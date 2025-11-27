package com.example.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

/**
 * Parcelable을 사용하는 User 모델
 * - Android에 최적화
 * - 더 빠른 성능
 * - 메모리 효율적
 */
@Parcelize
data class UserParcelable(
    val id: Int,
    val name: String,
    val email: String,
    val age: Int,
    val address: String,
) : Parcelable

/**
 * Serializable을 사용하는 User 모델
 * - Java 표준
 * - 구현이 간단
 * - 성능이 느림
 */
data class UserSerializable(
    val id: Int,
    val name: String,
    val email: String,
    val age: Int,
    val address: String,
) : Serializable
