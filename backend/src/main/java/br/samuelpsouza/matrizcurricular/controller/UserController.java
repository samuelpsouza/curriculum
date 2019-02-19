package br.samuelpsouza.matrizcurricular.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

}
