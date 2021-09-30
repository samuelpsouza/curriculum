package space.ssouza.curriculum.exceptions;

public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1734866593060367212L;

	public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
