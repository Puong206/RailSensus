package com.example.railsensus.repositori

import com.example.railsensus.apiservice.ServiceApi
import com.example.railsensus.modeldata.LoginRequest
import com.example.railsensus.modeldata.RegisterRequest

class RepositoriRailSensus(
    private val serviceApi: ServiceApi
) {
    //Auth
    suspend fun login(email: String, password: String) = safeApiCall {
        serviceApi.login(LoginRequest(email, password))
    }

    suspend fun register(username: String, email: String, password: String) =
        safeApiCall {
            serviceApi.register(RegisterRequest(username, email, password))
    }

    suspend fun getProfile(token: String) = safeApiCall {
        serviceApi.getProfile(token)
    }
}