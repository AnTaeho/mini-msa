package com.example.userservice.service

import com.example.userservice.domain.User
import com.example.userservice.dto.*
import com.example.userservice.feign.AuthFeignClient
import com.example.userservice.repository.UserRepository
import com.example.userservice.util.NicknameGenerator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(
    private val userRepository: UserRepository,
    private val authFeignClient: AuthFeignClient,
) {

    @Transactional
    fun joinUser(joinRequest: JoinRequest): UserResponse {
        checkEmail(joinRequest.email)
        val randomNickname = makeRandomNickname()
        val password = authFeignClient.encodePassword(PasswordDto(joinRequest.password)).result
        val user = User(
            email = joinRequest.email,
            password = password.value,
            nickname = randomNickname,
            userRole = joinRequest.userRole,
        )

        return UserResponse(userRepository.save(user).id)
    }

    private fun makeRandomNickname(): String {
        try {
            val randomNickname = NicknameGenerator.makeNickname()
            checkNickname(randomNickname)
            return randomNickname
        } catch (e: IllegalStateException) {
            return makeRandomNickname()
        }
    }

    private fun checkEmail(email: String) {
        if (userRepository.existsByEmail(email)) {
            throw IllegalStateException("이미 가입한 이메일 입니다.")
        }
    }

    private fun checkNickname(nickname: String) {
        if (userRepository.existsByNickname(nickname)) {
            throw IllegalStateException("이미 가입한 닉네임 입니다.")
        }
    }

    fun getUserInfo(emailDto: EmailDto): UserInfo {
        val user = getUser(emailDto.value)
        println(user.password)
        return UserInfo(
            user.email,
            user.password,
            user.userRole.name
        )
    }

    @Transactional
    fun updateRefreshToken(updateRefreshRequest: UpdateRefreshRequest) {
        val user = getUser(updateRefreshRequest.email)
        user.updateRefreshToken(updateRefreshRequest.refreshToken)
    }

    private fun getUser(email: String) =
        userRepository.findByEmail(email) ?: throw IllegalArgumentException("해당 유저는 존재 안함")

}