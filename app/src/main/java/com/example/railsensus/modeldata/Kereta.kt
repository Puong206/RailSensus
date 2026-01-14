package com.example.railsensus.modeldata

import kotlinx.serialization.Serializable

@Serializable
data class Kereta(
    val ka_id: Int,
    val nama_ka: String,
    val no_ka: String,
)
