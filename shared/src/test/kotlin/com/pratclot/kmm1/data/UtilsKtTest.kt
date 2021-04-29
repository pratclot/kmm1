package com.pratclot.kmm1.data

import org.junit.Test
import kotlin.test.assertEquals

internal class UtilsKtTest {

    @Test
    fun toTemps() {
        val data = listOf(
            """{"temp_cauldron": "5.56", "temp_heater": "6.31", "heater_status": "Off", "pump_status": "Off"}""" to Temps(
                5.56f,
                6.31f,
                Status.OFF,
                Status.OFF
            )
        )

        data.forEach {
            val expected = it.second
            val actual = it.first.toTemps()

            assertEquals(expected, actual)
        }
    }
}