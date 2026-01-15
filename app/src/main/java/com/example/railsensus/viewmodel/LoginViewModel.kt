package com.example.railsensus.viewmodel

import androidx.lifecycle.ViewModel
import com.example.railsensus.modeldata.UILoginState
import com.example.railsensus.modeldata.UIRegisterState
import com.example.railsensus.repositori.RepositoriRailSensus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel (
    private val repository: RepositoriRailSensus
) : ViewModel() {

    //Login State
    private val _loginState = MutableStateFlow(UILoginState())
    val loginState: StateFlow<UILoginState> = _loginState.asStateFlow()

    //Register State
    private val _registerState = MutableStateFlow(UIRegisterState())
    val registerState: StateFlow<UIRegisterState> = _registerState.asStateFlow()
}