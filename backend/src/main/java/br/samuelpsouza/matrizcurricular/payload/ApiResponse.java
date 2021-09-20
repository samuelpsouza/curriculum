package br.samuelpsouza.matrizcurricular.payload;

public class ApiResponse {
    private Boolean success;
    private String message;
    private Object data;

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResponse(final boolean success, final String message, final Object id) {
        this.success = success;
        this.message = message;
        this.data = id;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
