package com.nlp.tokenize.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.nlp.tokenize.model.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/nlp")
public class myController {
	String task;
	String userinfo;
	final Type type = Type.valueOf("PERSON");
	@Autowired
	private StanfordCoreNLP stanfordcorenlp;
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/info")
	public List<Object> information(@RequestBody final String input){
		CoreDocument coreDocument = new CoreDocument(input);
		stanfordcorenlp.annotate(coreDocument);
		List<CoreLabel> coreLabels = coreDocument.tokens();
		// show my appointment or show vaibhav's appointment or show my data

		for(CoreLabel coreLabel : coreLabels) {
			//printing the all the tokens
			String pos = coreLabel.getString(CoreAnnotations.PartOfSpeechAnnotation.class);

			switch(pos) {
				case "appointment":
					task = "appointment";
					break;
				case "data":
					task = "users";
					break;
				case "my":
					userinfo = "vaibhav";
					break;

			}
		}
		List<String> info = name(coreLabels, type);
		userinfo = info.get(0);
		Object[] objects = restTemplate.getForObject("http://localhost:5000"+ task + userinfo, Object[].class);
		return Arrays.asList(objects);
	}
	private List<String> name(List<CoreLabel> coreLabels, final Type type){
		return coreLabels.stream().filter(coreLabel -> type.getName().equalsIgnoreCase(coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
				.map(CoreLabel::originalText).collect(Collectors.toList());
	}
}