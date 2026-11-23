package com.example.mad_finalassignment_sportsclubmanagement.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.mad_finalassignment_sportsclubmanagement.api.WeatherClient
import com.example.mad_finalassignment_sportsclubmanagement.api.WeatherResponse
import kotlinx.coroutines.launch


class WeatherState(
    val status: String,
    val weather: WeatherResponse? = null,
    val message: String = ""
)
class WeatherViewModel : ViewModel() {

    var state by mutableStateOf(WeatherState("loading"))
        private set

    fun loadWeather() {
        viewModelScope.launch {
            state = WeatherState("loading")

            try {
                val response = WeatherClient.api.getWeather(
                    latitude = 7.4863,
                    longitude = 80.3647
                )
                val current = response.current

                if(
                    current == null ||
                    current.temperature_2m == null ||
                    current.weather_code == null
                ) {
                    state = WeatherState(
                        status = "empty",
                        message = "No weather data is available"
                    )
                } else {
                    state = WeatherState(
                        status = "success",
                        weather = response
                    )
                }
            } catch (e: Exception) {
                state = WeatherState(
                    status = "error",
                    message = "Unable to load weather"
                )
            }
        }
    }
    init {
        loadWeather()
    }
}