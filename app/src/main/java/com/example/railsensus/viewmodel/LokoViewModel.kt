package com.example.railsensus.viewmodel

import androidx.lifecycle.ViewModel
import com.example.railsensus.modeldata.Lokomotif
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
}