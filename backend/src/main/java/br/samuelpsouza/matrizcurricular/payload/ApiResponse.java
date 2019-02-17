package br.samuelpsouza.matrizcurricular.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponse {
    private Boolean success = false;
    private String message = "";
    private Object data;
}
