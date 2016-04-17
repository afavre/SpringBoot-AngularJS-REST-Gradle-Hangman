package com.hangman.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hangman.exception.LetterDuplicateException;
import com.hangman.model.Game;
import com.hangman.repository.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository repo;

	@Value("${app.maxscore}")
	private int maxScore;

	/**
	 * @param sessionId
	 * @return game
	 */
	public Game newGame(final String sessionId) {
		final Game game = new Game();
		final String word = DictionaryService.getRandomWord();
		final String emptyWord = word.replaceAll(".", "_");
		game.setSessionId(sessionId);
		game.setEnded(false);
		game.setSuccessful(false);
		game.setScore(maxScore);
		game.setWord(word);
		game.setCurrentWord(emptyWord);
		game.setLettersUsed(new ArrayList<Character>());
		return repo.save(game);
	}

	/**
	 * Get the game for the sessionId passed in parameter. Return a new game if
	 * not existing
	 *
	 * @param sessionId
	 * @return game
	 */
	public Game getCurrentGame(final String sessionId) {
		Game game = repo.findOne(sessionId);
		if (game == null) {
			game = newGame(sessionId);
		}
		return game;
	}

	/**
	 * @param sessionId
	 * @return game updated
	 */
	public Game showSolution(final String sessionId) {
		final Game game = repo.findOne(sessionId);
		game.setCurrentWord(game.getWord());
		game.setEnded(true);
		return repo.save(game);
	}

	/**
	 * @return all the games from database
	 */
	public List<Game> getAllGames() {
		return repo.findAll();
	}

	/**
	 * @param sessionId
	 * @param letter
	 * @return game updated
	 * @throws LetterDuplicateException
	 *             if a letter is used many times
	 */
	public Game play(final String sessionId, final char letter) throws LetterDuplicateException {
		Game game = repo.findOne(sessionId);
		final char letterNoCase = Character.toLowerCase(letter);
		if (game.getLettersUsed().contains(letterNoCase)) {
			throw new LetterDuplicateException();
		}
		game.addLetterUsed(letterNoCase);
		final int indice = game.getWord().indexOf(letterNoCase);
		// Incorrect letter
		if (indice == -1) {
			game.setScore(game.getScore() - 1);
			if (game.getScore() == 0) {
				game.setEnded(true);
			}
			// Correct letter
		} else {
			game = correctLetter(letterNoCase, game, indice);
		}
		return repo.save(game);
	}

	/**
	 * Workflow when the letter is present in the word to find
	 *
	 * @param letter
	 * @param game
	 * @param indice
	 * @return updated game
	 */
	private Game correctLetter(final char letter, final Game game, int indice) {
		final StringBuilder currentWord = new StringBuilder(game.getCurrentWord());
		currentWord.setCharAt(indice, letter);
		while (indice >= 0) {
			indice = game.getWord().indexOf(letter, indice + 1);
			if (indice >= 0) {
				currentWord.setCharAt(indice, letter);
			}
		}
		game.setCurrentWord(currentWord.toString());
		if (game.getCurrentWord().indexOf("_") == -1) {
			game.setSuccessful(true);
			game.setEnded(true);
		}
		return game;
	}

}
