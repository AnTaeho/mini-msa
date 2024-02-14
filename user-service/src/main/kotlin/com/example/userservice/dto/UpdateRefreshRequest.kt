package com.example.userservice.dto

data class UpdateRefreshRequest(
    val email: String,
    val refreshToken: String
)
