package com.example.myapitest.model

data class Car(
    val id: String,
    val value: CarValue
)

data class CarValue(
    val id: String,
    val imageUrl: String,
    val year: String,
    val name: String,
    val license: String,
    val place: CarLocation,
)

data class CarLocation (
    val latitude: Double,
    val longitude: Double
)