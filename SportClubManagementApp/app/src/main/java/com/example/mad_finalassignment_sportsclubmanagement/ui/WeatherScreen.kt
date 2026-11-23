package com.example.mad_finalassignment_sportsclubmanagement.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel,
    onBackClick: () -> Unit
) {
    val state = viewModel.state

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onBackClick
            ) {
                Text("Back")
            }
            Text(
                text = "Weather",
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Text(
            text = "Kurunegala, Sri Lanka",
            style = MaterialTheme.typography.titleLarge
        )
        when(state.status) {
            "loading" -> {
                CircularProgressIndicator()
                Text("Loading weather.....")
            }
            "success" -> {
                val current = state.weather?.current

                if(current != null) {
                    Text(
                        text = "${current.temperature_2m} celcius",
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = getWeatherDescription(current.weather_code),
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = "Humidity: ${
                            current.relative_humidity_2m?.let {"$it%"}
                                ?: "Unavailable"
                        }"
                    )
                    Text(
                        text = "Wins speed: ${
                            current.wind_speed_10m?.let {"$it km/h"}
                                ?: "Unavailable"
                        }"
                    )
                } else {
                    Text("No weather data is available")
                    Button(
                        onClick = {
                            viewModel.loadWeather()
                        }
                    ) {
                        Text("Retry")
                    }
                }
            }
            "empty" -> {
                Text("No weather data is available")
                Button(onClick = {
                    viewModel.loadWeather()
                }) {
                    Text("Retry")
                }
            }
            "error" -> {
                Text(state.message)
                Button(
                    onClick = {
                        viewModel.loadWeather()
                    }
                ) {
                    Text("Retry")
                }
            }
            else -> {
                Text("Unexpected weather status")

                Button(
                    onClick = {
                        viewModel.loadWeather()
                    }
                ) {
                    Text("Retry")
                }
            }
        }
    }
}

private fun getWeatherDescription(code: Int?): String {
    return when (code) {
        0 -> "Clear sky"
        1 -> "Mainly clear"
        2 -> "Partly cloudy"
        3 -> "Overcast"
        45, 48 -> "Foggy"
        51, 53, 55 -> "Drizzle"
        61, 63, 65 -> "Rain"
        71, 73, 75 -> "Snow"
        80, 81, 82 -> "Rain showers"
        95, 96, 99 -> "Thunderstorm"
        null -> "Condition unavailable"
        else -> "Unknown weather condition"
    }
}
