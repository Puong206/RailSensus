package com.example.railsensus.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RailSensusTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier.padding(bottom = 16.dp)
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 14.sp,
                color = RailSensusTheme.blueColor,
                fontFamily = RailSensusTheme.blueFontFamily,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            placeholder = {
                Text(
                    text = placeholder,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = RailSensusTheme.lightGrayColor,
                        fontFamily = RailSensusTheme.blueFontFamily
                    )
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "$label Icon",
                    tint = RailSensusTheme.lightGrayColor
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = RailSensusTheme.fieldBackgroundColor,
                focusedContainerColor = RailSensusTheme.fieldBackgroundColor,
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = RailSensusTheme.blueColor,
                cursorColor = RailSensusTheme.blueColor,
                disabledContainerColor = RailSensusTheme.fieldBackgroundColor,
                disabledBorderColor = Color.Transparent,
                disabledTextColor = RailSensusTheme.lightGrayColor
            ),
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = RailSensusTheme.blueColor,
                fontFamily = RailSensusTheme.blueFontFamily
            )
        )
    }
}
