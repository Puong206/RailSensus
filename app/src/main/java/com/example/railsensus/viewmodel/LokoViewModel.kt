package com.example.railsensus.viewmodel

import androidx.lifecycle.ViewModel
import com.example.railsensus.modeldata.Lokomotif
import com.example.railsensus.modeldata.StatistikLoko
import com.example.railsensus.modeldata.UILokomotifState
import com.example.railsensus.repositori.RepositoriRailSensus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LokoViewModel(
    private val repositori: RepositoriRailSensus
) : ViewModel() {
    //State

    //List Loko
    private val _lokoList = MutableStateFlow<List<Lokomotif>>(emptyList())
    val lokoList: StateFlow<List<Lokomotif>> = _lokoList.asStateFlow()

    //create/update
    private val _lokoFormState = MutableStateFlow(UILokomotifState())
    val lokoFormState: StateFlow<UILokomotifState> = _lokoFormState.asStateFlow()

    //select
    private val _selectedLoko = MutableStateFlow<Lokomotif?>(null)
    val selectedLoko: StateFlow<Lokomotif?> = _selectedLoko.asStateFlow()

    //statistik
    private val _statistik = MutableStateFlow<StatistikLoko?>(null)
    val statistik: StateFlow<StatistikLoko?> = _statistik.asStateFlow()

    //loading
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    //pesan error
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
}