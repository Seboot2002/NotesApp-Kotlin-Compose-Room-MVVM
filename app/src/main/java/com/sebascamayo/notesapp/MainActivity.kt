package com.sebascamayo.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sebascamayo.notesapp.features.feature_notes.presentation.notes.NotesScreen
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sebascamayo.notesapp.features.feature_notes.presentation.add_edit_note.AddEditNoteScreen
import com.sebascamayo.notesapp.features.feature_notes.presentation.util.Screen
import com.sebascamayo.notesapp.ui.theme.NotesAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint //Nos permite inyectar nuestros ViewModel
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(1, 1),
            navigationBarStyle = SystemBarStyle.light(1, 1)
        )
        setContent {
            NotesAppTheme{
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController() // Inicializacion del NavController
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NotesScreen.route
                    ) {
                        //Screens
                        composable(route = Screen.NotesScreen.route) {
                            NotesScreen(
                                navController = navController
                            )
                        }
                        composable(
                            route = Screen.AddEditNoteScreen.route + "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                name = "noteColor"
                                ) {
                                type = NavType.IntType
                                defaultValue = -1
                                }
                            )
                        ) {
                            val color = it.arguments?.getInt("noteColor") ?: -1 //Obtenemos el colorInt de los argumentos
                            AddEditNoteScreen(
                                navController = navController,
                                noteColor = color
                            )
                        }
                    }
                }
            }
        }
    }
}