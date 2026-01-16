package com.example.railsensus.view.user

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.railsensus.R
import com.example.railsensus.ui.component.RailSensusTheme
import com.example.railsensus.viewmodel.SensusViewModel
import com.example.railsensus.viewmodel.provider.RailSensusViewModel

@Composable
fun SensusDetailPage(
    sensusId: Int,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onValidClick: () -> Unit = {},
    onInvalidClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onReportClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onTambahFotoClick: () -> Unit = {},
    sensusViewModel: SensusViewModel = viewModel(factory = RailSensusViewModel.Factory)
) {
    val selectedSensus by sensusViewModel.selectedSensus.collectAsState()
    val isLoading by sensusViewModel.isLoading.collectAsState()
    val errorMessage by sensusViewModel.errorMessage.collectAsState()
    
    LaunchedEffect(sensusId) {
        sensusViewModel.loadSensusById(sensusId)
    }
    Scaffold(
        bottomBar = {
            SensusDetailBottomBar(
                onEditClick = onEditClick,
                onTambahFotoClick = onTambahFotoClick
            )
        }
    ) { paddingValues ->
        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = RailSensusTheme.blueColor)
                }
            }
            selectedSensus == null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ErrorOutline,
                            contentDescription = null,
                            tint = RailSensusTheme.lightGrayColor,
                            modifier = Modifier.size(64.dp)
                        )
                        Text(
                            text = errorMessage ?: "Data sensus tidak ditemukan",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = RailSensusTheme.lightGrayColor,
                                fontFamily = RailSensusTheme.blueFontFamily
                            )
                        )
                        TextButton(onClick = onBackClick) {
                            Text("Kembali")
                        }
                    }
                }
            }
            else -> {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFF8F9FB))
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Header Image with Train Info
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            ) {
                // Train Image Placeholder
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(RailSensusTheme.blueColor)
                ) {
                    Icon(
                        imageVector = Icons.Default.Train,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.1f),
                        modifier = Modifier
                            .size(120.dp)
                            .align(Alignment.Center)
                    )
                }
                
                // Gradient Overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.5f)
                                )
                            )
                        )
                )
                
                // Back Button
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                        .size(40.dp)
                        .background(
                            color = Color.White.copy(alpha = 0.9f),
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = RailSensusTheme.blueColor
                    )
                }
                
                // Train Info
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // CC Badge
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = RailSensusTheme.orangeColor.copy(alpha = 0.9f)
                    ) {
                        Text(
                            text = "CC",
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = Color.White,
                                fontFamily = RailSensusTheme.blueFontFamily,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }
                    
                    // Train Name
                    Text(
                        text = selectedSensus?.nama_ka ?: "Unknown Train",
                        style = TextStyle(
                            fontSize = 26.sp,
                            color = Color.White,
                            fontFamily = RailSensusTheme.orangeFontFamily,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    
                    // Lokomotif Number
                    Text(
                        text = selectedSensus?.nomor_seri ?: "N/A",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.White,
                            fontFamily = RailSensusTheme.blueFontFamily
                        )
                    )
                }
            }
            
            // Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Trust Score Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Tingkat Kepercayaan
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Shield,
                                        contentDescription = null,
                                        tint = RailSensusTheme.blueColor,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = "Tingkat Kepercayaan",
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            color = RailSensusTheme.lightGrayColor,
                                            fontFamily = RailSensusTheme.blueFontFamily
                                        )
                                    )
                                }
                                
                                Row(
                                    verticalAlignment = Alignment.Bottom,
                                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                                ) {
                                    Text(
                                        text = "${selectedSensus?.trust_score ?: 0}",
                                        style = TextStyle(
                                            fontSize = 32.sp,
                                            color = RailSensusTheme.blueColor,
                                            fontFamily = RailSensusTheme.orangeFontFamily,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Text(
                                        text = "/10",
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            color = RailSensusTheme.lightGrayColor,
                                            fontFamily = RailSensusTheme.blueFontFamily
                                        ),
                                        modifier = Modifier.padding(bottom = 4.dp)
                                    )
                                }
                            }
                            
                            // Akurasi Data
                            Column(
                                horizontalAlignment = Alignment.End,
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "Akurasi Data",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        color = RailSensusTheme.lightGrayColor,
                                        fontFamily = RailSensusTheme.blueFontFamily
                                    )
                                )
                                Text(
                                    text = "${(selectedSensus?.trust_score ?: 0) * 10}%",
                                    style = TextStyle(
                                        fontSize = 24.sp,
                                        color = Color(0xFF4CAF50),
                                        fontFamily = RailSensusTheme.orangeFontFamily,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }
                        
                        // Progress Bar
                        LinearProgressIndicator(
                            progress = { (selectedSensus?.trust_score?.toFloat() ?: 0f) / 10f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = Color(0xFF4CAF50),
                            trackColor = Color(0xFFE0E0E0)
                        )
                        
                        // Valid/Invalid Buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedButton(
                                onClick = onValidClick,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color(0xFF4CAF50)
                                ),
                                border = ButtonDefaults.outlinedButtonBorder.copy(
                                    width = 1.5.dp
                                )
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ThumbUp,
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Text(
                                        text = "Valid",
                                        style = TextStyle(
                                            fontFamily = RailSensusTheme.blueFontFamily,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    )
                                    Text(
                                        text = "(0)",
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            fontFamily = RailSensusTheme.blueFontFamily
                                        )
                                    )
                                }
                            }
                            
                            OutlinedButton(
                                onClick = onInvalidClick,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = Color.Red
                                ),
                                border = ButtonDefaults.outlinedButtonBorder.copy(
                                    width = 1.5.dp
                                )
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ThumbDown,
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Text(
                                        text = "Invalid",
                                        style = TextStyle(
                                            fontFamily = RailSensusTheme.blueFontFamily,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    )
                                    Text(
                                        text = "(0)",
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            fontFamily = RailSensusTheme.blueFontFamily
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
                
                // Informasi Detail Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(4.dp)
                                    .height(20.dp)
                                    .background(
                                        color = RailSensusTheme.orangeColor,
                                        shape = RoundedCornerShape(2.dp)
                                    )
                            )
                            Text(
                                text = "Informasi Detail",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = RailSensusTheme.blueColor,
                                    fontFamily = RailSensusTheme.orangeFontFamily,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .background(
                                        color = RailSensusTheme.blueColor.copy(alpha = 0.1f),
                                        shape = RoundedCornerShape(8.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Tag,
                                    contentDescription = null,
                                    tint = RailSensusTheme.blueColor,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            
                            Column {
                                Text(
                                    text = "Tipe Lokomotif",
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        color = RailSensusTheme.lightGrayColor,
                                        fontFamily = RailSensusTheme.blueFontFamily
                                    )
                                )
                                Text(
                                    text = "CC",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        color = RailSensusTheme.blueColor,
                                        fontFamily = RailSensusTheme.blueFontFamily,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                        }
                    }
                }
                
                // Kontributor Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Kontributor",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = RailSensusTheme.blueColor,
                                    fontFamily = RailSensusTheme.orangeFontFamily,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Avatar with status indicator
                                Box {
                                    Box(
                                        modifier = Modifier
                                            .size(48.dp)
                                            .background(
                                                color = RailSensusTheme.blueColor,
                                                shape = CircleShape
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = selectedSensus?.username?.take(2)?.uppercase() ?: "??",
                                            style = TextStyle(
                                                fontSize = 16.sp,
                                                color = Color.White,
                                                fontFamily = RailSensusTheme.blueFontFamily,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                    }
                                    
                                    // Online indicator
                                    Box(
                                        modifier = Modifier
                                            .size(12.dp)
                                            .background(
                                                color = Color(0xFF4CAF50),
                                                shape = CircleShape
                                            )
                                            .border(
                                                width = 2.dp,
                                                color = Color.White,
                                                shape = CircleShape
                                            )
                                            .align(Alignment.BottomEnd)
                                    )
                                }
                                
                                Column {
                                    Text(
                                        text = selectedSensus?.username ?: "Unknown User",
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            color = RailSensusTheme.blueColor,
                                            fontFamily = RailSensusTheme.blueFontFamily,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
                
                // Report Button
                TextButton(
                    onClick = onReportClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = null,
                            tint = RailSensusTheme.lightGrayColor,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Laporkan kesalahan data",
                            style = TextStyle(
                                fontSize = 13.sp,
                                color = RailSensusTheme.lightGrayColor,
                                fontFamily = RailSensusTheme.blueFontFamily
                            )
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
            }
        }
    }
}

@Composable
fun SensusDetailBottomBar(
    onEditClick: () -> Unit,
    onTambahFotoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = onEditClick,
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = RailSensusTheme.blueColor
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    width = 1.5.dp
                )
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Edit Data",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = RailSensusTheme.blueFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
            
            Button(
                onClick = onTambahFotoClick,
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = RailSensusTheme.orangeColor,
                    contentColor = Color.White
                )
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Tambah Foto",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = RailSensusTheme.blueFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }
    }
}
