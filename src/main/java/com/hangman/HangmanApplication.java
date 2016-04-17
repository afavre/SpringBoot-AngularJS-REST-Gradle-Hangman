package com.hangman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hangman.service.DictionaryService;

@SpringBootApplication
public class HangmanApplication {

	public static void main(final String[] args) {
		SpringApplication.run(HangmanApplication.class, args);
		DictionaryService.loadDictionnary();
	}
}
