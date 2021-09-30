package space.ssouza.curriculum.internal.err;

public class ApiValidationError extends ApiSubError {
	private String object;
	private String field;
	private Object rejectedValue;
	private String message;

	ApiValidationError(String object, String message) {
		this.object = object;
		this.message = message;
	}

	public ApiValidationError(final String object, final String field, final Object rejectedValue,
			final String message) {
		this.object = object;
		this.field = field;
		this.rejectedValue = rejectedValue;
		this.message = message;
	}

	public String getObject() {
		return object;
	}

	public String getField() {
		return field;
	}

	public Object getRejectedValue() {
		return rejectedValue;
	}

	public String getMessage() {
		return message;
	}

}
