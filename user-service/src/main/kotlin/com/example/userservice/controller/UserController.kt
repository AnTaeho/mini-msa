package com.example.userservice.controller

import com.example.userservice.dto.*
import com.example.userservice.service.UserService
import com.example.userservice.util.CommonResponse
import com.example.userservice.util.EmptyDto
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService,
) {

    @PostMapping("/join")
    fun joinUser(@RequestBody joinRequest: JoinRequest): CommonResponse<UserResponse> {
        return CommonResponse(userService.joinUser(joinRequest))
    }

    @GetMapping("/user-info")
    fun getUserInfo(@RequestBody emailDto: EmailDto): CommonResponse<UserInfo> {
        return CommonResponse(userService.getUserInfo(emailDto))
    }

    @PatchMapping("/refresh-token")
    fun updateRefreshToken(@RequestBody updateRefreshRequest: UpdateRefreshRequest): CommonResponse<EmptyDto> {
        userService.updateRefreshToken(updateRefreshRequest)
        return CommonResponse.EMPTY
    }
}