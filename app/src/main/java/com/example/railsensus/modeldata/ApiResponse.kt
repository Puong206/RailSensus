package com.example.railsensus.modeldata

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val message: String,
    val sensus_id: Int? = null,
    val loko_id: Int? = null,
    val ka_id: Int? = null,
    val user: UserData? = null
)

@Serializable
data class ErrorResponse(
    val message: String
)