package com.nlp.tokenize.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@RestController
@RequestMapping(value="/nlp")
public class myController {
	String task;
	String userinfo="";
	@Autowired
	private StanfordCoreNLP stanfordcorenlp;
	private final restService rest = new restService();

	@GetMapping(value="/info")
	public ResponseEntity<String> information(@RequestBody final String input){
		CoreDocument coreDocument = new CoreDocument(input);
		stanfordcorenlp.annotate(coreDocument);
		List<CoreLabel> coreLabels = coreDocument.tokens();
		// show my appointment or show vaibhav's appointment or show my data

		for(CoreLabel coreLabel : coreLabels) {
			//printing the all the tokens
			String value = coreLabel.originalText();
			String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
			if(pos.equals("NNP")){
				userinfo="value";
			}

			switch(value) {
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

		String url=task+"/"+userinfo;
		return rest.getService(url);
	}
}