package br.com.ractecnologia.exception;

public class LicensingModelException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LicensingModelException() {
		super();
	}

	public LicensingModelException(String message) {
		super(message);
	}

	public LicensingModelException(String message, Throwable cause) {
		super(message, cause);
	}

	public LicensingModelException(Throwable cause) {
		super(cause);
	}

}
