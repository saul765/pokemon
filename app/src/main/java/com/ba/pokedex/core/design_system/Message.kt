package com.ba.pokedex.core.design_system

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.ba.pokedex.core.data.EMPTY_CHARACTER

@Composable
fun ProcessMessage(modifier: Modifier = Modifier, message: String = EMPTY_CHARACTER) {


    MaterialTheme() {
        Text(
            textAlign = TextAlign.Center,
            modifier = modifier,
            text = message,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}