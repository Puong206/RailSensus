package com.example.railsensus.modeldata

import kotlinx.serialization.Serializable

data class VoteRequest(
    val tipe_vote: String
)

@Serializable
data class VoteResponse(
    val message: String,
    val trust_score: Int
)