package com.sebascamayo.notesapp.features.feature_phrase.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PhraseItem(phrase: String, author: String, isLoading: Boolean){

    Box (
        modifier = Modifier
            .background(Color(0xFFFFFFFF))
            .fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 26.dp)
    ) {
        if (isLoading) {
            /*CircularProgressIndicator(
                modifier = Modifier.width(2.dp),
                color = Color.Black,
                trackColor = Color.Gray
            )*/
            Text("Loading..." , modifier = Modifier.align(Alignment.Center))
        }
        else
        {
            Column(
            ) {
                Text(
                    text = phrase,
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "- $author",
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}