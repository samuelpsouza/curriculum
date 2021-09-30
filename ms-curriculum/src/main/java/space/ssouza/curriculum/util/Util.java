package space.ssouza.curriculum.util;

import space.ssouza.curriculum.payload.ApiResponse;

public class Util {
	private Util() {
		// Empty constructor
	}

	public static ApiResponse success(final String message, final Object data) {
		return new ApiResponse(true, message, data);
	}
}
