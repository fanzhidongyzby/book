package com.upc.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Book Server Exception")
public class BookException extends Exception {

	private static String mergeString(Object... message) {
		String msg = "";
		for (Object m : message) {
			msg += m.toString() + " ";
		}
		msg = msg.trim();
		return msg;
	}

	public BookException() {
		super();
	}

	public BookException(Object... message) {
		super(mergeString(message));
	}
}
