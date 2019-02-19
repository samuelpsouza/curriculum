package br.samuelpsouza.matrizcurricular.util;

import br.samuelpsouza.matrizcurricular.payload.ApiResponse;

import java.util.List;

public class Util {
    private Util() {
    }

    public static ApiResponse success(String message, Object data) {
        return new ApiResponse(true, "message", data);
    }
}
