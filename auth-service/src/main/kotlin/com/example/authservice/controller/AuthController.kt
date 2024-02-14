package com.example.authservice.controller

import com.example.authservice.dto.LoginRequest
import com.example.authservice.dto.PasswordDto
import com.example.authservice.dto.TokenResponse
import com.example.authservice.service.JwtService
import com.example.authservice.util.CommonResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): CommonResponse<TokenResponse> {
        val authenticationToken = loginRequest.toAuthenticationToken()
        val authentication = authenticationManager.authenticate(authenticationToken)
        return CommonResponse(jwtService.makeTokenResponse(authentication.name))
    }

    @GetMapping("/encode-password")
    fun encodePassword(@RequestBody passwordDto: PasswordDto): CommonResponse<PasswordDto> {
        return CommonResponse(PasswordDto(
            passwordEncoder.encode(passwordDto.value)
        ))
    }

}