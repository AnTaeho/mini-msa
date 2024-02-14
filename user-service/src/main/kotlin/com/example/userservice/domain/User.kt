package com.example.userservice.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var id: Long = 0L,

    @field:Email
    var email: String,

    @field:NotNull
    var password: String,

    @field:NotNull
    var nickname: String,

    @Enumerated(EnumType.STRING)
    var userRole: UserRole = UserRole.USER_BUYER,

    var refreshToken: String = "",
    var profile: String = "https://shophub-image.s3.ap-northeast-2.amazonaws.com/defualt/avatar_woman.png",

    @CreatedDate
    var createdAt: LocalDateTime? = null,
    @LastModifiedDate
    var modifiedAt: LocalDateTime? = null

) {

    fun updateRefreshToken(refreshToken: String) {
        this.refreshToken = refreshToken
    }

}