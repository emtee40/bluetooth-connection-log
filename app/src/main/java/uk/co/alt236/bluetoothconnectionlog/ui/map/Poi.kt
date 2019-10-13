package uk.co.alt236.bluetoothconnectionlog.ui.map

import java.io.Serializable

data class Poi(
    val title: String,
    val latitude: Double,
    val longitude: Double,
    val accuracy: Float
) : Serializable