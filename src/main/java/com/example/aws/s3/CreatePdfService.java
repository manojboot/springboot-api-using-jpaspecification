package com.example.aws.s3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
public class CreatePdfService {

	@Autowired
	private SpringTemplateEngine templateEngine;

	@Autowired
	private ActorRepository actorRepository;

	@SuppressWarnings("unchecked")
	public CommonResponse createPdf() throws IOException {
		
		CommonResponse response = new CommonResponse();
		ActorsResponse actosResponse = new ActorsResponse();
		List<Actor> actors = actorRepository.findAll();
		ObjectMapper mapper = new ObjectMapper();
		Context context = new Context();
		context.setLocale(Locale.US);
		Map<String, Object> actorsMap = mapper.convertValue(actosResponse, Map.class);
		actorsMap.put("actors", actors);
		context.setVariables(actorsMap);

		String reportTemplate = templateEngine.process("actordetailreport.html", context);
		try {
			log.info("inside");
			FileOutputStream outPutStream = new FileOutputStream("D:\\Documents\\actor-list.pdf");
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(reportTemplate);
			renderer.layout();
			log.info(outPutStream.toString());
			renderer.createPDF(outPutStream);
			outPutStream.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		response.setDescription("SUCCESS");
		response.setStatusCode("200");
		return response;
	}
}
