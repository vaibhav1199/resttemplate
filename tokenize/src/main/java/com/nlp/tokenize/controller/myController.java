package com.nlp.tokenize.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.nlp.tokenize.model.Type;

import org.springframework.beans.factory.annotation.Autowired;
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
	final Type type = Type.valueOf("PERSON");
	@Autowired
	private StanfordCoreNLP stanfordcorenlp;

	private restService rest= new restService();


	@GetMapping(value="/info")
	public void information(@RequestBody final String input){
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
		if(userinfo.equals("vaibhav")) {
			userinfo = info.get(0);
		}
		String url=task+userinfo;
		rest.getService(url);
	}
	private List<String> name(List<CoreLabel> coreLabels, final Type type){
		return coreLabels.stream().filter(coreLabel -> type.getName().equalsIgnoreCase(coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
				.map(CoreLabel::originalText).collect(Collectors.toList());
	}
}
