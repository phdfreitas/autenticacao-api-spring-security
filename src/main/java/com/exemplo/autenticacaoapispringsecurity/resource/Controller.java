package com.exemplo.autenticacaoapispringsecurity.resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping
    public String welcome(){
        return "Welcome to example application";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ADMIN', 'USERS')")
    public String users(){
        return "Authorized user";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String admin(){
        return "Authorized admin";
    }
}
