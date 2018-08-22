package br.com.ractecnologia.exception;

public class MoneyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public MoneyException() {
		super();
	}

	public MoneyException(String message) {
		super(message);
	}

	public MoneyException(String message, Throwable cause) {
		super(message, cause);
	}

	public MoneyException(Throwable cause) {
		super(cause);
	}
}
