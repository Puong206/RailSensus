package com.example.railsensus.modeldata

import kotlinx.serialization.Serializable

@Serializable
data class Lokomotif(
    val loko_id: Int,
    val nomor_seri: String,
    val dipo_induk: String,
    val status: String?
)

@Serializable
data class StatistikLoko(
    val total: Int,
    val total_dipo: Int
)