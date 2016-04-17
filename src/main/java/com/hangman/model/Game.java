package com.hangman.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Game {

	@Id
	@Column
	private String sessionId;

	@Column
	private int score;

	@Column
	private boolean isEnded;

	@Column
	private boolean isSuccessful;

	@Column
	private String currentWord;

	@Column
	private String word;

	@Column
	private ArrayList<Character> lettersUsed;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(final String sessionId) {
		this.sessionId = sessionId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(final int score) {
		this.score = score;
	}

	public boolean isEnded() {
		return isEnded;
	}

	public void setEnded(final boolean isEnded) {
		this.isEnded = isEnded;
	}

	public boolean isSuccessful() {
		return isSuccessful;
	}

	public void setSuccessful(final boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}

	public String getCurrentWord() {
		return currentWord;
	}

	public void setCurrentWord(final String currentWord) {
		this.currentWord = currentWord;
	}

	public String getWord() {
		return word;
	}

	public void setWord(final String word) {
		this.word = word;
	}

	public ArrayList<Character> getLettersUsed() {
		return lettersUsed;
	}

	public void setLettersUsed(final ArrayList<Character> lettersUsed) {
		this.lettersUsed = lettersUsed;
	}

	public void addLetterUsed(final char letter) {
		lettersUsed.add(letter);
	}

}
