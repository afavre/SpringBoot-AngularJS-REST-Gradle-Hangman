package com.hangman.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {

	private static List<String> words;

	/**
	 * Load the text file which contain words
	 */
	public static void loadDictionnary() {
		words = new ArrayList<String>();

		try (ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(new String[] {})) {
			final Resource resource = appContext.getResource("classpath:dictionary.txt");
			final InputStream is = resource.getInputStream();
			final BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = br.readLine()) != null) {
				words.add(line);
			}
			br.close();

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return a random word from the dictionary file
	 */
	public static String getRandomWord() {
		final int randomInt = new Random().nextInt(words.size() - 1);
		return words.get(randomInt);
	}

}
