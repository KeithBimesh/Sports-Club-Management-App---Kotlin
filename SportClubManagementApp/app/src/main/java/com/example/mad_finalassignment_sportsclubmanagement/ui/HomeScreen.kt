package com.example.mad_finalassignment_sportsclubmanagement.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.material3.Card
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.example.mad_finalassignment_sportsclubmanagement.R


@Composable
fun HomeScreen(
    onTeamsClick: () -> Unit,
    onMatchesClick: () -> Unit,
    onWeatherClick: () -> Unit,
    onStatisticsClick: () -> Unit,
    darkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "KTD SPORTS CLUB",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Manage your teams, players and matches",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(50.dp))

        Image(
            painter = painterResource(
                id = R.drawable.sport_image
            ),
            contentDescription = "All sports in a one image",
            modifier = Modifier.fillMaxWidth().height(190.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(52.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "Dark Mode",
                style = MaterialTheme.typography.titleMedium
            )
            Switch(
                checked = darkMode,
                onCheckedChange = onDarkModeChange
            )
        }
        Button(
            onClick = onStatisticsClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Statistics")
        }

        Spacer(modifier = Modifier.weight(1f))

        Card(
            modifier = Modifier.fillMaxWidth().height(90.dp).
            padding(bottom = 8.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth().height(90.dp).padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onTeamsClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Teams")
                }
                Button(
                    onClick = onMatchesClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Matches")
                }
                Button(
                    onClick = onWeatherClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Weather")
                }

            }

        }
    }
}