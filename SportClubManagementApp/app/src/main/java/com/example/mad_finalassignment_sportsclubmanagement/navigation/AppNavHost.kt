package com.example.mad_finalassignment_sportsclubmanagement.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import com.example.mad_finalassignment_sportsclubmanagement.ui.HomeScreen
import com.example.mad_finalassignment_sportsclubmanagement.database.AppDB
import com.example.mad_finalassignment_sportsclubmanagement.ui.AddPlayerScreen
import com.example.mad_finalassignment_sportsclubmanagement.ui.AddTeamScreen
import com.example.mad_finalassignment_sportsclubmanagement.ui.PlayerDetailScreen
import com.example.mad_finalassignment_sportsclubmanagement.ui.TeamDetailsScreen
import com.example.mad_finalassignment_sportsclubmanagement.ui.TeamsScreen
import com.example.mad_finalassignment_sportsclubmanagement.ui.MatchScreen
import com.example.mad_finalassignment_sportsclubmanagement.ui.AddMatchScreen
import com.example.mad_finalassignment_sportsclubmanagement.ui.TeamMatchesScreen
import com.example.mad_finalassignment_sportsclubmanagement.ui.MatchDetailsScreen
import com.example.mad_finalassignment_sportsclubmanagement.ui.WeatherScreen
import com.example.mad_finalassignment_sportsclubmanagement.ui.StatisticsScreen
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.PlayerViewModel
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.ViewModelFactory
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.TeamViewModel
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.MatchViewModel
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.PlayerDetailsViewModel
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.TeamDetailsViewModel
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.TeamMatchesViewModel
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.MatchDetailsViewModel
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.WeatherViewModel
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.PreferencesViewModel
import com.example.mad_finalassignment_sportsclubmanagement.viewmodel.StatisticsViewModel

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    val preferencesViewModel: PreferencesViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            HomeScreen(
                onTeamsClick = {
                    navController.navigate("teams")
                },
                onMatchesClick = {
                    navController.navigate("matches")
                },
                onWeatherClick = {
                    navController.navigate("weather")
                },
                onStatisticsClick = {
                    navController.navigate("statistics")
                },
                darkMode = preferencesViewModel.darkMode,
                onDarkModeChange = {enabled ->
                    preferencesViewModel.ChangeDarkMode(enabled)
                }
            )
        }

        composable("teams") {
            val context = LocalContext.current
            val db = AppDB.get(context)

            val viewModel: TeamViewModel = viewModel(
                factory = ViewModelFactory(
                    teamDao = db.teamDao(),
                    playerDao = db.playerDao(),
                    matchDao = db.matchDao()
                )
            )
            val Added = navController.currentBackStackEntry
                ?.savedStateHandle
                ?.get<Boolean>("teamAdded")
            val Updated = navController.currentBackStackEntry
                ?.savedStateHandle
                ?.get<Boolean>("teamUpdated")
            val Deleted = navController.currentBackStackEntry
                ?.savedStateHandle
                ?.get<Boolean>("teamDeleted")

            if(Added == true) {
                viewModel.refresh()
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("teamAdded", false)
            }
            if(Updated == true) {
                viewModel.refresh()
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("teamUpdated", false)
            }
            if(Deleted == true) {
                viewModel.refresh()
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("teamDeleted", false)
            }
            TeamsScreen(
                vm =  viewModel,
                onTeamClick = { teamID ->
                    navController.navigate("teamDetail/$teamID")
                },
                onAddTeamClick = {
                    navController.navigate("addTeam")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("addTeam") {
            val context = LocalContext.current
            val db = AppDB.get(context)

            val viewModel: TeamViewModel = viewModel(
                factory = ViewModelFactory(
                    teamDao = db.teamDao(),
                    playerDao = db.playerDao(),
                    matchDao = db.matchDao()
                )
            )
            AddTeamScreen(
                viewModel = viewModel,
                onBackClick = {
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("teamAdded", true)

                    navController.popBackStack()
                }
            )
        }

        composable("teamDetail/{teamID}") { backStackEntry ->
            val teamID = backStackEntry.arguments
                ?.getString("teamID")
                ?.toIntOrNull()

            if(teamID != null) {
                val context = LocalContext.current
                val db = AppDB.get(context)

                val teamViewModel: TeamViewModel = viewModel(
                    factory = ViewModelFactory(
                        teamDao = db.teamDao(),
                        playerDao = db.playerDao(),
                        matchDao = db.matchDao()
                    )
                )

                val playerViewModel: PlayerViewModel = viewModel(
                    factory = ViewModelFactory(
                        teamDao = db.teamDao(),
                        playerDao = db.playerDao(),
                        matchDao = db.matchDao(),
                        teamID = teamID
                    )
                )
                val teamDetailsViewModel: TeamDetailsViewModel = viewModel(
                    factory = ViewModelFactory(
                        teamDao = db.teamDao(),
                        playerDao = db.playerDao(),
                        matchDao = db.matchDao(),
                        teamID = teamID
                    )
                )
                val playerAdded = navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.get<Boolean>("playerAdded")
                val playerUpdated = navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.get<Boolean>("playerUpdated")
                val playerDeleted = navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.get<Boolean>("playerDeleted")

                if(playerAdded == true)
                {
                    playerViewModel.refresh()
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("playerAdded", false)
                }
                if(playerUpdated == true) {
                    playerViewModel.refresh()
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("playedUpdated",false)
                }
                if(playerDeleted == true) {
                    playerViewModel.refresh()
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("playerDeleted", false)
                }
                TeamDetailsScreen(
                    viewModel = teamDetailsViewModel,
                    playerViewModel = playerViewModel,
                    onAddPlayerClick = {
                        navController.navigate("addPlayer/$teamID")
                    },
                    onPlayerClick = { playerID ->
                        navController.navigate("playerDetail/$playerID")
                    },
                    onMatchesClick = {
                        navController.navigate("teamMatches/$teamID")
                    },
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onUpdateTeamClick ={
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("teamUpdated", true)
                        navController.popBackStack()
                    },
                    onDeleteTeamClick = {
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("teamDeleted", true)
                        navController.popBackStack()
                    }
                )
            }
        }

        composable("teamMatches/{teamID}") { backStackEntry ->
            val teamID = backStackEntry.arguments
                ?.getString("teamID")
                ?.toIntOrNull()

            if(teamID != null) {
                val context = LocalContext.current
                val db = AppDB.get(context)
                val viewModel: TeamMatchesViewModel = viewModel(
                    factory = ViewModelFactory(
                        teamDao = db.teamDao(),
                        playerDao = db.playerDao(),
                        matchDao = db.matchDao(),
                        teamID = teamID
                    )
                )
                val matchAdded = navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.get<Boolean>("matchAdded")

                if(matchAdded == true) {
                    viewModel.refresh()
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("matchAdded", false)
                }
                TeamMatchesScreen(
                    viewModel = viewModel,

                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable("addPlayer/{teamID}") { backStackEntry ->
            val teamID = backStackEntry.arguments
                ?.getString("teamID")
                ?.toIntOrNull()

            if(teamID != null) {
                val context = LocalContext.current
                val db = AppDB.get(context)
                val playerViewModel: PlayerViewModel = viewModel(
                    factory = ViewModelFactory(
                        teamDao = db.teamDao(),
                        playerDao = db.playerDao(),
                        matchDao = db.matchDao(),
                        teamID = teamID
                    )
                )
                AddPlayerScreen(
                    teamID = teamID,
                    viewModel = playerViewModel,
                    onPlayerAdded = {
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("playerAdded", true)

                        navController.popBackStack()
                    },
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable("playerDetail/{playerID}") { backStackEntry ->
            val playerID = backStackEntry.arguments
                ?.getString("playerID")
                ?.toIntOrNull()

            if(playerID != null) {
                val context = LocalContext.current
                val db = AppDB.get(context)

                val playerDetailsViewModel: PlayerDetailsViewModel = viewModel(
                    factory = ViewModelFactory(
                        teamDao = db.teamDao(),
                        playerDao = db.playerDao(),
                        matchDao = db.matchDao(),
                        playerID = playerID
                    )
                )
                PlayerDetailScreen(
                    viewModel = playerDetailsViewModel ,
                    onPlayerUpdate = {
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("playerUpdated", true)
                        navController.popBackStack()
                    },
                    onPlayerDelete = {
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("playerDeleted", true)
                        navController.popBackStack()
                    },
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable("matches") {
            val context = LocalContext.current
            val db = AppDB.get(context)

            val viewModel: MatchViewModel = viewModel(
                factory = ViewModelFactory(
                    teamDao = db.teamDao(),
                    playerDao = db.playerDao(),
                    matchDao = db.matchDao(),
                    )
            )
            val matchAdded = navController.currentBackStackEntry
                ?.savedStateHandle
                ?.get<Boolean>("matchAdded")
            val matchUpdated = navController.currentBackStackEntry
                ?.savedStateHandle
                ?.get<Boolean>("matchUpdated")
            val matchDeleted = navController.currentBackStackEntry
                ?.savedStateHandle
                ?.get<Boolean>("matchDeleted")

            if (matchAdded == true) {
                viewModel.refresh()
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("matchAdded", false)
            }
            if(matchUpdated == true) {
                viewModel.refresh()
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("matchUpdated", false)
            }
            if(matchDeleted == true)
            {
                viewModel.refresh()
                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("matchDeleted", false)
            }
            MatchScreen(
                viewModel = viewModel,
                onAddMatchClick = {
                    navController.navigate("addMatch")
                    },
                onMatchClick = {matchID ->
                    navController.navigate("matchDetail/$matchID")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable("addMatch") {
            val context = LocalContext.current
            val db = AppDB.get(context)
            val viewModel: MatchViewModel = viewModel(
                factory = ViewModelFactory(
                    teamDao = db.teamDao(),
                    playerDao = db.playerDao(),
                    matchDao = db.matchDao()
                )
            )
            AddMatchScreen(
                viewModel = viewModel,
                onMatchAdded = {
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("matchAdded", true)

                    navController.popBackStack()
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable("matchDetail/{matchID}") { backStackEntry ->
            val matchID = backStackEntry.arguments
                ?.getString("matchID")
                ?.toIntOrNull()
            if(matchID != null){
                val context = LocalContext.current
                val db = AppDB.get(context)

                val viewModel: MatchDetailsViewModel = viewModel(
                    factory = ViewModelFactory(
                        teamDao = db.teamDao(),
                        playerDao = db.playerDao(),
                        matchDao = db.matchDao(),
                        matchID = matchID
                    )
                )
                MatchDetailsScreen(
                    viewModel = viewModel,
                    onMatchUpdate = {
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("matchUpdated", true)
                        navController.popBackStack()
                    },
                    onMatchDelete = {
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("matchDeleted", true)
                        navController.popBackStack()
                    },
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable("weather") {
            val viewModel: WeatherViewModel = viewModel()

            WeatherScreen(
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable("statistics"){
            val context = LocalContext.current
            val db = AppDB.get(context)

            val viewModel: StatisticsViewModel = viewModel(
                factory = ViewModelFactory(
                    teamDao = db.teamDao(),
                    playerDao = db.playerDao(),
                    matchDao = db.matchDao()
                )
            )
            StatisticsScreen(
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
