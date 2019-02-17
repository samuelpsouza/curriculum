package br.samuelpsouza.matrizcurricular.controller;

import br.samuelpsouza.matrizcurricular.payload.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class IndexController {
    @GetMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> index() {
        return ResponseEntity.ok(new ApiResponse(true, "No donuts for you"));
    }
}
