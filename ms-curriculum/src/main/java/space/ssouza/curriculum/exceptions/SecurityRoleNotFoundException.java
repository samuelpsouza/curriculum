package space.ssouza.curriculum.exceptions;

public final class SecurityRoleNotFoundException extends Exception {
	private static final long serialVersionUID = -4521692636465275768L;

	public SecurityRoleNotFoundException() {
		super();
	}

	public SecurityRoleNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public SecurityRoleNotFoundException(final String message) {
		super(message);
	}

	public SecurityRoleNotFoundException(final Throwable cause) {
		super(cause);
	}
}
