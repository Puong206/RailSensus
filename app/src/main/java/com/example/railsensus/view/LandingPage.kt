package com.example.railsensus.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.railsensus.R

@Composable
fun LandingPage(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {}
) {
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(top = 20.dp))
    {
        val blueFontFamily = FontFamily(Font(R.font.plusjakartasans_medium))
        val orangeFontFamily = FontFamily(Font(R.font.plusjakartasans_bold))
        val blueColor = Color(0xFF153D77)
        val orangeColor = Color(0xFFFF9428)
        Column(
            modifier = Modifier.padding(top = 48.dp, start = 24.dp, end = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
        ) {
            Text(
                fontSize = 28.sp,
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = blueColor,
                            fontFamily = orangeFontFamily
                        )
                    ) {
                        append("Selamat ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = orangeColor,
                            fontFamily = orangeFontFamily
                        )
                    ) {
                        append("Datang")
                    }
                }
            )
            Text(
                fontSize = 18.sp,
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = blueColor,
                            fontFamily = blueFontFamily
                        )
                    ) {
                        append("Gabung dengan ")
                    }

                    withStyle(
                        style = SpanStyle(
                            color = orangeColor,
                            fontFamily = orangeFontFamily
                        )
                    ) {
                        append("komunitas")
                    }

                    withStyle(
                        style = SpanStyle(
                            color = blueColor,
                            fontFamily = blueFontFamily
                        )
                    ) {
                        append(" untuk berbagi ")
                    }

                    withStyle(
                        style = SpanStyle(
                            color = orangeColor,
                            fontFamily = orangeFontFamily
                        )
                    ) {
                        append("data sensus lokomotif")
                    }

                    withStyle(
                        style = SpanStyle(
                            color = blueColor,
                            fontFamily = blueFontFamily
                        )
                    ) {
                        append(" yang akurat. ")
                    }

                    withStyle(
                        style = SpanStyle(
                            color = orangeColor,
                            fontFamily = orangeFontFamily
                        )
                    ) {
                        append("Hunting")
                    }

                    withStyle(
                        style = SpanStyle(
                            color = blueColor,
                            fontFamily = blueFontFamily
                        )
                    ) {
                        append(" jadi ")
                    }

                    withStyle(
                        style = SpanStyle(
                            color = orangeColor,
                            fontFamily = orangeFontFamily
                        )
                    ) {
                        append("lebih mudah")
                    }

                    withStyle(
                        style = SpanStyle(
                            color = blueColor,
                            fontFamily = blueFontFamily
                        )
                    ) {
                        append(".")
                    }
                }
            )
            Row(modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onLoginClick,
                    modifier = Modifier
                        .width(112.dp)
                        .height(40.dp),
                    shape = RoundedCornerShape(size = 12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = blueColor,
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "Login",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.plusjakartasans_light))
                        )
                    )
                }

                OutlinedButton(
                    onClick = onRegisterClick,
                    modifier = Modifier
                        .width(112.dp)
                        .height(40.dp),
                    shape = RoundedCornerShape(size = 12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White,
                        contentColor = blueColor
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "Register",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.plusjakartasans_light))
                        )
                    )
                }
            }
        }
        val imageLanding = painterResource(R.drawable.landing_image)
        Image(
            painter = imageLanding,
            contentDescription = "Landing Image",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
    }
}