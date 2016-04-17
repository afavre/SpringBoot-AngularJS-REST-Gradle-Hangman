package com.hangman.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Letter already used")
public class LetterDuplicateException extends Exception {
	private static final long serialVersionUID = 4444133299674850472L;
}
