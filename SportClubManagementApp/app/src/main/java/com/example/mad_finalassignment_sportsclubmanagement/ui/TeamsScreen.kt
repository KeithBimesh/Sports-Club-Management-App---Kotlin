package com.example.mad_finalassignment_sportsclubmanagement.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mad_finalassignment_sportsclubmanagement.R
import com.example.mad_finalassignment_sportsclubmanagement.model.Team
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.TeamViewModel

@Composable
fun TeamsScreen(
    vm: TeamViewModel,
    onTeamClick: (Int) -> Unit,
    onAddTeamClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {

        Image(
            painter = painterResource(
                id = R.drawable.sport_team
            ),
            contentDescription = "Sport team on a ground",
            modifier = Modifier.fillMaxWidth().height(190.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Your Teams",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(vm.teams) { team ->
                TeamItem(
                    team = team,
                    onClick = {
                        onTeamClick(team.id)
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onBackClick,
            ) {
                Text("Back")
            }
            Button(
                onClick = onAddTeamClick,
            ) {
                Text("Add Team")
            }
        }
    }
}

@Composable
fun TeamItem(
    team: Team,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = team.name,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = team.sport,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}