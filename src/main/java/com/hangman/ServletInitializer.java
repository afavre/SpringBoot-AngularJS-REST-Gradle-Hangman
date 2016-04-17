package com.hangman;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import com.hangman.service.DictionaryService;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		DictionaryService.loadDictionnary();
		return application.sources(HangmanApplication.class);
	}

}
