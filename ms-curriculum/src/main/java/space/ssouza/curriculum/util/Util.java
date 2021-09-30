package space.ssouza.curriculum.util;

import java.util.List;

import space.ssouza.curriculum.payload.ApiResponse;

public class Util {
    private Util() {
    }

    public static ApiResponse success(String message, Object data) {
        return new ApiResponse(true, "message", data);
    }
}
