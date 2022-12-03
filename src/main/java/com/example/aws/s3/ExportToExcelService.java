package com.example.aws.s3;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ExportToExcelService {

	@Autowired
	private ActorRepository actorRepository;

	private Sheet sheet;
	private Workbook workbook;

	@SuppressWarnings("unchecked")
	public CommonResponse exportToExcel() throws IOException {
		
		CommonResponse response = new CommonResponse();
		ActorsResponse actosResponse = new ActorsResponse();
		List<Actor> actors = actorRepository.findAll();
		ObjectMapper mapper = new ObjectMapper();
		
		workbook.createSheet("Action");
		response.setDescription("SUCCESS");
		response.setStatusCode("200");
		return response;
	}
}
