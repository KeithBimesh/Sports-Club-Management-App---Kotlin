package com.example.mad_finalassignment_sportsclubmanagement.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.transformable
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mad_finalassignment_sportsclubmanagement.model.Team
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.PlayerViewModel
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.TeamDetailsViewModel

@Composable
fun TeamDetailsScreen(
    viewModel: TeamDetailsViewModel,
    playerViewModel: PlayerViewModel,
    onAddPlayerClick: () -> Unit,
    onPlayerClick: (Int) -> Unit,
    onMatchesClick: () -> Unit,
    onUpdateTeamClick: () -> Unit,
    onDeleteTeamClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val team = viewModel.team

    var edit by rememberSaveable { mutableStateOf(false) }
    var name by rememberSaveable { mutableStateOf("") }
    var sport by rememberSaveable { mutableStateOf("") }

    Column(

        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Button(
            onClick = onBackClick
        ) {
            Text("Back to Teams")
        }

        Spacer(modifier = Modifier.padding(top = 8.dp))
        Text(
            text = "Team Details",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(top = 12.dp))

        val team = viewModel.team

        if (team != null) {
            if (!edit) {
                Text(
                    text = "Team: ${team.name}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Sport: ${team.sport}",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.padding(top = 16.dp))

                Text(
                    text = "Players",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.padding(top = 12.dp))

                LazyColumn {
                    items(playerViewModel.players) { player ->
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable {
                                onPlayerClick(player.id)
                            }
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = player.name
                                )
                                Text(
                                    text = "Age: ${player.age}"
                                )
                                Text(
                                    text = "Position: ${player.position}"
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                /*Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {*/

                    Button(
                        onClick = onAddPlayerClick,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Add a Player")
                    }
                    Button(
                        onClick = onMatchesClick,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("View Matches")
                    }

                Spacer(modifier = Modifier.height(10.dp))

                /*Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {*/
                    Button(
                        onClick = {
                            name = team.name
                            sport = team.sport
                            edit = true
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Update")
                    }

                    Button(
                        onClick = {
                            viewModel.delete(team) {
                                onDeleteTeamClick()
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Delete")
                    }

            } else {
                Text(
                    text = "Update Team",
                    style = MaterialTheme.typography.titleLarge
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = {name = it},
                    label = {Text("Team Name")},
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = sport,
                    onValueChange = {sport = it},
                    label = {Text("Sport")},
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        val updatedTeam = Team(
                            id = team.id,
                            name = name,
                            sport = sport
                        )
                        viewModel.update(updatedTeam) {
                            onUpdateTeamClick()
                        }
                    },
                    enabled = name.isNotBlank() && sport.isNotBlank()
                ) {
                    Text("Save Changes")
                }
                Button(
                    onClick = {
                        edit = false
                        name = team.name
                        sport = team.sport
                    }
                ) {
                    Text("Cancel")
                }
            }
        } else {
            Text(
                text = "Team not found"
            )
        }
    }
}
