package com.example.railsensus.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RailSensusPageHeader(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(bottom = 24.dp)
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 24.sp,
                color = RailSensusTheme.blueColor,
                fontFamily = RailSensusTheme.orangeFontFamily,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = subtitle,
            style = TextStyle(
                fontSize = 14.sp,
                color = RailSensusTheme.lightGrayColor,
                fontFamily = RailSensusTheme.blueFontFamily
            )
        )
    }
}
