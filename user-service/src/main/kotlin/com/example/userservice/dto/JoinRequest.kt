package com.example.userservice.dto

import com.example.userservice.domain.UserRole

data class JoinRequest(
    val email: String,
    val password: String,
    val userRole: UserRole
)
