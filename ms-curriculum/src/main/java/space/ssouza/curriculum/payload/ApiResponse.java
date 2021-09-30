package space.ssouza.curriculum.payload;

public class ApiResponse {
	private Boolean success;
	private String message;
	private Object data;

	public ApiResponse(final Boolean success, final String message) {
		this.success = success;
		this.message = message;
	}

	public ApiResponse(final boolean success, final String message, final Object data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
