package fr.bsm.location.domain.common.exception;

public class GeocodingException extends BusinessException {

	private static final long serialVersionUID = -1L;

	public GeocodingException(final String message) {
		super(message);
	}

	public GeocodingException() {
		super();
	}

	@Override
	public String getCode() {
		return null; 
	}
}
