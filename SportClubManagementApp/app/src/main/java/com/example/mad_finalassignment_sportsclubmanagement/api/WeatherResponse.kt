package com.example.mad_finalassignment_sportsclubmanagement.api

data class WeatherResponse(
    val current: CurrentWeather?
)
data class CurrentWeather(
    val temperature_2m: Double?,
    val relative_humidity_2m: Int?,
    val weather_code: Int?,
    val wind_speed_10m: Double?
)