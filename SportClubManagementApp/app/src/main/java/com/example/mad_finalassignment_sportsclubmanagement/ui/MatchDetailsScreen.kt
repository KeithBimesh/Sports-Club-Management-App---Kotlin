package com.example.mad_finalassignment_sportsclubmanagement.ui

import android.content.Intent
import android.provider.CalendarContract
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.example.mad_finalassignment_sportsclubmanagement.model.Match
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.MatchDetailsViewModel

@Composable
fun MatchDetailsScreen(
    viewModel: MatchDetailsViewModel,
    onMatchUpdate: () -> Unit,
    onMatchDelete: () -> Unit,
    onBackClick: () -> Unit
) {
    val match = viewModel.match
    val context = LocalContext.current

    var edit by rememberSaveable { mutableStateOf(false) }
    var opponent by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    var location by rememberSaveable{ mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "Match Details",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(top = 12.dp))

        if(match != null) {

            if(!edit) {
                Text(
                    text = "Opponent: ${match.opponent}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Date: ${match.date}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Location: ${match.location}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Team ID: ${match.teamID}",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        val shareText = """
                            Sports Club Match Details
                            
                            Opponent: ${match.opponent}
                            Date: ${match.date}
                            Location: ${match.location}
                            Team ID: ${match.teamID}
                        """.trimIndent()

                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, shareText)
                        }
                        context.startActivity(
                            Intent.createChooser(shareIntent, "Share Match")
                        )
                    }
                ) {
                    Text("Share Match")

                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        val intent = Intent(Intent.ACTION_INSERT).apply {
                            data = CalendarContract.Events.CONTENT_URI

                            putExtra(
                                CalendarContract.Events.TITLE,
                                "Sports Match vs ${match.opponent}"
                            )
                            putExtra(
                                CalendarContract.Events.EVENT_LOCATION,
                                match.location
                            )
                            putExtra(
                                CalendarContract.Events.DESCRIPTION,
                                "Team ID: ${match.teamID}"
                            )
                            putExtra(
                                CalendarContract.Events.ALL_DAY,
                                true
                            )
                        }
                        context.startActivity(intent)
                    }
                ) {
                    Text("Add to Calendar")
                }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        opponent = match.opponent
                        date = match.date
                        location = match.location
                        edit = true
                    }
                ) {
                    Text("Update")
                }

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.delete(match) {
                            onMatchDelete()
                        }
                    }
                ) {
                    Text("Delete")
                }
            }
            else {
                OutlinedTextField(
                    value = opponent,
                    onValueChange = { opponent = it},
                    label = {Text("Opponent")},
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = date,
                    onValueChange = {date = it},
                    label = {Text("Date")},
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = location,
                    onValueChange = {location = it},
                    label = {Text("Location")},
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        val updatedMatch = Match(
                            id = match.id,
                            opponent = opponent,
                            date = date,
                            location = location,
                            teamID = match.teamID
                        )
                        viewModel.update(updatedMatch) {
                            onMatchUpdate()
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
        }
        else{
            Text(
                text = "Match not found"
            )
            Button(
                onClick = onBackClick
            ) {
                Text("Back")
            }
        }
    }
}