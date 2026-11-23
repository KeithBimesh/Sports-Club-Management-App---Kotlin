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
import com.example.mad_finalassignment_sportsclubmanagement.model.Match
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.MatchViewModel

@Composable
fun AddMatchScreen(
    viewModel: MatchViewModel,
    onMatchAdded: () -> Unit,
    onBackClick: () -> Unit
) {
    var Teamid by rememberSaveable { mutableStateOf("") }
    var opponent by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    var location by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "Add Match"
        )
        OutlinedTextField(
            value = Teamid,
            onValueChange = { Teamid = it },
            label = { Text("Team ID") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = opponent,
            onValueChange = { opponent = it },
            label = { Text("Opponent") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Date") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val teamID = Teamid.toIntOrNull()

                if(teamID != null) {
                    val match = Match(
                        opponent = opponent,
                        date = date,
                        location = location,
                        teamID = teamID
                    )
                    viewModel.add(match) {
                        onMatchAdded()
                    }
                }
            }
        ) {
            Text("Save Match")
        }
        Button(
            onClick = onBackClick
        ) {
            Text("Back")
        }
    }
}