package fr.bsm.location.domain.common.exception;

public class AlreadyExistException extends BusinessException {

	private static final long serialVersionUID = -1L;

	public AlreadyExistException(final String message) {
		super(message);
	}

	public AlreadyExistException() {
		super();
	}

	@Override
	public String getCode() {
		return null; 
	}
}
