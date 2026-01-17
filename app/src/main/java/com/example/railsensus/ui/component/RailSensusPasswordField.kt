package com.example.railsensus.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RailSensusPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    var passwordVisible by remember { mutableStateOf(false) }
    
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
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Sembunyikan password" else "Tampilkan password",
                        tint = RailSensusTheme.lightGrayColor
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
