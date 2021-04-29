package com.pratclot.kmm1.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Temps(
    val temp_cauldron: Float,
    val temp_heater: Float,
    val heater_status: Status,
    val pump_status: Status
)

@Serializable
enum class Status {
    @SerialName("On")
    ON,

    @SerialName("Off")
    OFF
}
