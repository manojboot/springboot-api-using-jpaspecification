package com.example.jpaspecification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public class FilmService {

	private FilmRepository filmRepository;
	private EntityManager entityManager;

	public FilmService(FilmRepository filmRepository,EntityManager entityManager) {
		super();
		this.filmRepository = filmRepository;
		this.entityManager = entityManager;
	}
	
	public FilmResponse getAllFilmDetails(){
		FilmResponse response = new FilmResponse();
		List<Film> films = filmRepository.findAll();
		response.setFilms(films);
		return response;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FilmInfoResponse getFilmDetails(FilmRequest filmRequest){
		FilmInfoResponse response = new FilmInfoResponse();
		Pageable page = PageRequest.of(filmRequest.getPageNumber(), filmRequest.getSize(),Sort.by(Order.desc("title")));
		List<Integer> filmIds = getFilmIds(filmRequest);
		response.setFilms(getPagedObject(filmIds, page));
		if(CollectionUtils.isNotEmpty(filmIds)) {
			response.setStatus("Sucsess");
			response.setStatusCode("200");
			response.setStatusDescription("Data Reterieved Successfully");
		}else {
			response.setStatus("Sucsess");
			response.setStatusCode("200");
			response.setStatusDescription("No Data Reterieved");
		}
		return response;
	}

	private List<Integer> getFilmIds(FilmRequest filmRequest){
		List<Integer> filmIds = new ArrayList<>();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> query = criteriaBuilder.createTupleQuery();
		Root<Film> root = query.from(Film.class);
		List<Predicate> predicates = null;
		predicates = getPredicates(filmRequest,root,query,criteriaBuilder);
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		query.multiselect(root.get("filmId").alias("filmId"));
		query.orderBy(criteriaBuilder.desc(root.get("filmId")));
		TypedQuery<Tuple> typedQuery = entityManager.createQuery(query);
		typedQuery.getResultList().stream().forEach(tuple->filmIds.add(tuple.get("filmId", Integer.class)));
		return filmIds;
		
	}
	public List<Predicate> getPredicates(FilmRequest filmRequest, Root<Film> root, CriteriaQuery<?> query,
			CriteriaBuilder criteriaBuilder) {
		
			List<Predicate> predicates = new ArrayList<>();
			applyQueryFilters(filmRequest,root,criteriaBuilder,predicates);
			query.distinct(true);
			return predicates;
		
	}

	@SuppressWarnings("rawtypes")
	private void applyQueryFilters(FilmRequest filmRequest, Root<Film> root, CriteriaBuilder criteriaBuilder,
			List<Predicate> predicates) {
		if(StringUtils.isNotBlank(filmRequest.getDescription())){
			String wordDescription = StringUtils.toRootLowerCase(filmRequest.getDescription());
			predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + wordDescription + "%"));
		}
		if(StringUtils.isNotBlank(filmRequest.getFilmTitle())){
			String filmTitle = StringUtils.toRootUpperCase(filmRequest.getFilmTitle());
			predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("title")), "%" + filmTitle + "%"));
		}
	}
	
	public PageImpl<FilmInfo> getPagedObject(List<Integer> filmIds,Pageable pageRequest){
		PageImpl<FilmInfo> pageImpl = new PageImpl<>();
		PagedListHolder<Integer> page = new PagedListHolder<>(filmIds);
		page.setPageSize(pageRequest.getPageSize());
		page.setPage(pageRequest.getPageNumber());
		pageImpl.setTotalElements(page.getNrOfElements());
		pageImpl.setNumberOfElements(page.getPageList().size());
		pageImpl.setSize(pageRequest.getPageSize());
		pageImpl.setContent(getFilmSummaryInfo(page.getPageList()));
		return pageImpl;
		
	}
	
	public List<FilmInfo> getFilmSummaryInfo(List<Integer> filmIds){
		List<FilmInfo> films = new ArrayList<>();
		List<Film> source = filmRepository.findAllById(filmIds);
		source.forEach(x->{
			FilmInfo filmInfo = new FilmInfo();
			filmInfo.setDescription(x.getDescription());
			filmInfo.setTitle(x.getTitle());
			filmInfo.setLastUpdateDate(x.getLastUpdateDate());
			filmInfo.setReleaseYear(x.getReleaseYear());
			filmInfo.setRentalRate(x.getRentalRate());
			filmInfo.setFilmId(x.getFilmId());
			films.add(filmInfo);
		});
		return films;
	}
	
//	public List<FilmInfo> mapPageObject(List<Integer> filmIds){
//		var modelMapper = new ModelMapper();
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		return filmIds.stream().map(pSource->mapObject(modelMapper, pSource)).collect(Collectors.toList());
//	}
	
//	public FilmInfo mapObject(ModelMapper modelMapper,Integer source) {
//		FilmInfo filmInfo = FilmService.mapObject(modelMapper, source, FilmInfo.class);
//		mapFilmInfoObject(source, filmInfo);
//		return filmInfo;
//	}
//	
//	private void mapFilmInfoObject(Film source, FilmInfo filmInfo) {
//		filmInfo.setDescription(source.getDescription());
//		filmInfo.setTitle(source.getTitle());
//		filmInfo.setReleaseYear(source.getReleaseYear());
//		filmInfo.setRentalRate(source.getRentalRate());
//		filmInfo.setLastUpdateDate(source.getLastUpdateDate());
//		filmInfo.setLength(source.getLength());
//	}
//
//	public static <S,T> T mapList(List<S> source,Class<T> targetClass) {
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		return modelMapper.map(source, targetClass);
//	}
}
