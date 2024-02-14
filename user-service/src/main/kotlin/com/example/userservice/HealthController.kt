package com.example.userservice

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {

    @GetMapping("/api/v1/users/health")
    fun health(): String = "Welcome to the UserService!"

}