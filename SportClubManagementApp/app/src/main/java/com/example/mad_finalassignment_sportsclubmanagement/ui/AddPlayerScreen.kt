package com.example.mad_finalassignment_sportsclubmanagement.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mad_finalassignment_sportsclubmanagement.model.Player
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.PlayerViewModel

@Composable
fun AddPlayerScreen(
    teamID: Int,
    viewModel: PlayerViewModel,
    onPlayerAdded: () -> Unit,
    onBackClick: () -> Unit
) {
    var name by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }
    var position by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Add a Player")

        OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            label = { Text("Player Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = age,
            onValueChange = {age = it},
            label = { Text("Player Age") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = position,
            onValueChange = {position = it},
            label = { Text("Player Position") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val player = Player(
                    name = name,
                    age = age.toIntOrNull() ?: 0,
                    position = position,
                    teamID = teamID
                )
                viewModel.add(player) {
                    onPlayerAdded()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Player")
        }

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }
}