package com.example.mad_finalassignment_sportsclubmanagement.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mad_finalassignment_sportsclubmanagement.R
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.MatchViewModel

@Composable
fun MatchScreen(
    viewModel: MatchViewModel,
    onAddMatchClick: () -> Unit,
    onMatchClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val Matches = viewModel.matches

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.match_image
            ),
            contentDescription = "Sport team on a ground",
            modifier = Modifier.fillMaxWidth().height(180.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Matches",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        LazyColumn {
            items(Matches) { match ->
                Card(
                    onClick = {
                        onMatchClick(match.id)
                    },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Opponent: ${match.opponent}")
                        Text("Date: ${match.date}")
                        Text("Location: ${match.location}")
                        Text("Team ID: ${match.teamID}")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onBackClick
            ) {
                Text("Back")
            }
            Button(
                onClick = onAddMatchClick
            ) {
                Text("Add Match")
            }
        }
    }
}