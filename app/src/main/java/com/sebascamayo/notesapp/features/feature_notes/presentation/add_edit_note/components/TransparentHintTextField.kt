package com.sebascamayo.notesapp.features.feature_notes.presentation.add_edit_note.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun TransparentHintTextFieldTitle(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintInvisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean= false,
    onFocusChange: (FocusState) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        BasicTextField(
            value = text,
            textStyle = TextStyle(fontSize = 32.sp),
            onValueChange = onValueChange,
            singleLine = singleLine,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChange(it)
                }
        )
        if (isHintInvisible) {
            Text(text = hint, style = textStyle, color = Color.DarkGray)
        }
    }
}