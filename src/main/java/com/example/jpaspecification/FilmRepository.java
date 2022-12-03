package com.example.jpaspecification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer>,JpaSpecificationExecutor<Film>{

	@EntityGraph(type = EntityGraphType.FETCH,attributePaths = {
			"filmId",
			"title",
			"description",
			"releaseYear",
			"length",
			"rentalRate",
			"lastUpdateDate"
	})
	@Override
	public Page<Film> findAll(Specification<Film> spec,Pageable pageable);
}
