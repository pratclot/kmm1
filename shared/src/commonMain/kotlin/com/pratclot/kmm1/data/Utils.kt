package com.pratclot.kmm1.data

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun String.toTemps() = Json.decodeFromString<Temps>(this)
