package com.example.mad_finalassignment_sportsclubmanagement.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mad_finalassignment_sportsclubmanagement.model.Player
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.PlayerDetailsViewModel

@Composable
fun PlayerDetailScreen(
    viewModel: PlayerDetailsViewModel,
    onPlayerUpdate: () -> Unit,
    onPlayerDelete: () -> Unit,
    onBackClick: () -> Unit
) {

    val player = viewModel.player

    var edit by rememberSaveable { mutableStateOf(false) }
    var name by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }
    var position by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {

        Text(
            text = "Player Details"
        )

        if (player != null) {

            if (!edit) {

                Text(
                    text = "Name: ${player.name}"
                )

                Text(
                    text = "Age: ${player.age}"
                )

                Text(
                    text = "Position: ${player.position}"
                )

                Text(
                    text = "Team ID: ${player.teamID}"
                )

                Button(
                    onClick = {
                        name = player.name
                        age = player.age.toString()
                        position = player.position
                        edit = true
                    }
                ) {
                    Text("Update")
                }

                Button(
                    onClick = {
                        viewModel.delete(player) {
                            onPlayerDelete()
                        }
                    }
                ) {
                    Text("Delete")
                }
            } else {

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = age,
                    onValueChange = { age = it },
                    label = { Text("Age") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = position,
                    onValueChange = { position = it },
                    label = { Text("Position") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        val NewAge = age.toIntOrNull()

                        if (NewAge != null) {
                            val updatedPlayer = Player(
                                id = player.id,
                                name = name,
                                age = NewAge,
                                position = position,
                                teamID = player.teamID
                            )
                            viewModel.update(updatedPlayer) {
                                onPlayerUpdate()
                            }
                        }
                    }
                ) {
                    Text("Save Changes")
                }
                Button(
                    onClick = {
                        edit = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        } else {
            Text(
                text = "Player not found"
            )
        }
        Button(
            onClick = onBackClick
        ) {
            Text("Back")
        }
    }
}
