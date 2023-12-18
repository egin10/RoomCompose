package com.ginsebu.roomcompose.location

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun LocationButton(
    onEvent: (LocationEvent) -> Unit,
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Button(
            modifier = Modifier
                .width(160.dp)
                .clip(RoundedCornerShape(8.dp)),
            onClick = { onEvent(LocationEvent.Start) }
        ) {
            Text(text = "START")
        }
        Spacer(modifier = Modifier.width(10.dp))
        Button(
            modifier = Modifier
                .width(160.dp)
                .clip(RoundedCornerShape(8.dp)),
            onClick = { onEvent(LocationEvent.Stop) }
        ) {
            Text(text = "STOP")
        }
    }
}