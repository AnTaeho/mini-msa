package com.example.userservice.feign

import com.example.userservice.dto.PasswordDto
import com.example.userservice.util.CommonResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "user-service", url = "http://localhost:8000/api/v1/auth")
@Qualifier(value = "user-service")
interface AuthFeignClient {

    @GetMapping("/encode-password")
    fun encodePassword(@RequestBody passwordDto: PasswordDto): CommonResponse<PasswordDto>

}