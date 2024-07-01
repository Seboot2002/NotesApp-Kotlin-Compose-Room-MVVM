package com.sebascamayo.notesapp.features.feature_notes.presentation.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PhraseItem(phrase: String, author: String){

    Box (
        modifier = Modifier
            .background(Color(0xFFA2A2A2))
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        Column(
        ) {
            Text(
                text = phrase,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "- $author",
            )
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}