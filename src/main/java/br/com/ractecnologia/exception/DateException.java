package br.com.ractecnologia.exception;

public class DateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DateException() {
		super();
	}

	public DateException(String message) {
		super(message);
	}

	public DateException(String message, Throwable cause) {
		super(message, cause);
	}

	public DateException(Throwable cause) {
		super(cause);
	}

}