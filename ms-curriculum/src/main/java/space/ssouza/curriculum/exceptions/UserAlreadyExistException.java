package space.ssouza.curriculum.exceptions;

public class UserAlreadyExistException extends RuntimeException {
	private static final long serialVersionUID = 3981233046030507295L;

	public UserAlreadyExistException() {
        super();
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }

    public UserAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
