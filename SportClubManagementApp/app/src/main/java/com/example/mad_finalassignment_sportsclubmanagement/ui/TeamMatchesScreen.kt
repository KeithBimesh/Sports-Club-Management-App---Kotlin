package com.example.mad_finalassignment_sportsclubmanagement.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.TeamMatchesViewModel

@Composable
fun TeamMatchesScreen(
    viewModel: TeamMatchesViewModel,
    onBackClick: () -> Unit
) {
    val matches = viewModel.matches

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "Team Matches"
        )

        LazyColumn {
            items(matches) { match ->

                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Opponent: ${match.opponent}")
                        Text("Date: ${match.date}")
                        Text("Location: ${match.location}")
                    }
                }
            }
        }
        Button(
            onClick = onBackClick
        ) {
            Text("Back")
        }
    }
}