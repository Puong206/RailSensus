package com.example.railsensus.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.railsensus.RailSensusApp

fun CreationExtras.railSensusApplication(): RailSensusApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RailSensusApp)

