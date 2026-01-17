package com.example.railsensus.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.railsensus.R

@Composable
fun RailSensusLogo(
    modifier: Modifier = Modifier,
    logoHeight: Dp = 52.dp
) {
    Image(
        painter = painterResource(R.drawable.railsensus_logo),
        contentDescription = "Rail Sensus Logo",
        contentScale = ContentScale.Fit,
        modifier = modifier.height(logoHeight)
    )
}
