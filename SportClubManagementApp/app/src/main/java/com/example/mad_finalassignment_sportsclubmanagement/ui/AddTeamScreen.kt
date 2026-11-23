package com.example.mad_finalassignment_sportsclubmanagement.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mad_finalassignment_sportsclubmanagement.model.Team
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.TeamViewModel

@Composable
fun AddTeamScreen(
    viewModel: TeamViewModel,
    onBackClick: () -> Unit,
) {
    var TeamName by rememberSaveable { mutableStateOf("") }
    var Sport by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Add Team"
        )

        OutlinedTextField(
            value = TeamName,
            onValueChange = {TeamName = it},
            label = {Text("Team Name")},
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = Sport,
            onValueChange = {Sport = it},
            label = {Text("Sport")},
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {viewModel.add(
                    Team(
                        name = TeamName,
                        sport = Sport
                    )
                )
                onBackClick()
            },

            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Team")
        }

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancel")
        }
    }
}