package com.hangman.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hangman.exception.LetterDuplicateException;
import com.hangman.model.Game;
import com.hangman.service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {

	@Autowired
	private GameService gameService;

	@RequestMapping(method = RequestMethod.GET)
	public Game findGame(final HttpSession session) {
		return gameService.getCurrentGame(session.getId());
	}

	@RequestMapping(value = "/{letter}", method = RequestMethod.GET)
	public Game playGame(final HttpSession session, @PathVariable final char letter) throws LetterDuplicateException {
		return gameService.play(session.getId(), letter);
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public Game newGame(final HttpSession session) {
		return gameService.newGame(session.getId());
	}

	@RequestMapping(value = "/solution", method = RequestMethod.GET)
	public Game showSolution(final HttpSession session) {
		return gameService.showSolution(session.getId());
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Game> getAllGames() {
		return gameService.getAllGames();
	}

}
