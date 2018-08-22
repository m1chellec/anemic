package br.com.ractecnologia.exception;

public class CustomerSatusException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerSatusException() {
		super();
	}

	public CustomerSatusException(String message) {
		super(message);
	}

	public CustomerSatusException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerSatusException(Throwable cause) {
		super(cause);
	}

}
