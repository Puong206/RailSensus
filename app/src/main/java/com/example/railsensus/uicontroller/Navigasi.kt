package com.example.railsensus.uicontroller

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.railsensus.view.LandingPage
import com.example.railsensus.view.auth.LoginPage
import com.example.railsensus.view.auth.RegisterPage
import com.example.railsensus.view.home.DashboardPage
import com.example.railsensus.view.lokomotif.LokoDetailPage
import com.example.railsensus.view.lokomotif.LokoPage
import com.example.railsensus.view.sensus.SensusDetailPage
import com.example.railsensus.view.sensus.SensusPage
import com.example.railsensus.view.user.UserPage
import com.example.railsensus.viewmodel.LoginViewModel
import com.example.railsensus.viewmodel.provider.RailSensusViewModel

/**
 * Sealed class untuk mendefinisikan semua route dalam aplikasi
 */
sealed class RailSensusScreen(val route: String) {
    object Landing : RailSensusScreen("landing")
    object Login : RailSensusScreen("login")
    object Register : RailSensusScreen("register")
    object Dashboard : RailSensusScreen("dashboard")
    object SensusPage : RailSensusScreen("sensusPage")
    object SensusDetail : RailSensusScreen("sensusDetail/{sensusId}") {
        fun createRoute(sensusId: Int) = "sensusDetail/$sensusId"
    }
    object LokoPage : RailSensusScreen("lokoPage")
    object LokoDetail : RailSensusScreen("lokoDetail/{lokoId}") {
        fun createRoute(lokoId: Int) = "lokoDetail/$lokoId"
    }
    object UserPage : RailSensusScreen("userPage")
}

/**
 * Navigation Host untuk RailSensus
 * Mengelola semua navigasi antar screen dalam aplikasi
 */
@Composable
fun RailSensusNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = RailSensusScreen.Landing.route
) {
    val loginViewModel: LoginViewModel = viewModel(factory = RailSensusViewModel.Factory)
    
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // Landing Page
        composable(route = RailSensusScreen.Landing.route) {
            LandingPage(
                onLoginClick = {
                    navController.navigate(RailSensusScreen.Login.route)
                },
                onRegisterClick = {
                    navController.navigate(RailSensusScreen.Register.route)
                }
            )
        }
        
        // Login Page
        composable(route = RailSensusScreen.Login.route) {
            LoginPage(
                onBackClick = {
                    navController.popBackStack()
                },
                onLoginSuccess = {
                    navController.navigate(RailSensusScreen.Dashboard.route) {
                        popUpTo(RailSensusScreen.Landing.route) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate(RailSensusScreen.Register.route)
                }
            )
        }
        
        // Register Page
        composable(route = RailSensusScreen.Register.route) {
            RegisterPage(
                onBackClick = {
                    navController.popBackStack()
                },
                onRegisterSuccess = {
                    navController.navigate(RailSensusScreen.Login.route) {
                        popUpTo(RailSensusScreen.Landing.route)
                    }
                },
                onLoginClick = {
                    navController.navigate(RailSensusScreen.Login.route)
                }
            )
        }
        
        // Dashboard Page
        composable(route = RailSensusScreen.Dashboard.route) {
            DashboardPage(
                onSensusClick = { sensusId ->
                    navController.navigate(RailSensusScreen.SensusDetail.createRoute(sensusId))
                },
                onBottomNavClick = { index ->
                    handleBottomNavigation(navController, index)
                },
                onTambahSensusClick = {
                    navController.navigate(RailSensusScreen.SensusPage.route)
                }
            )
        }
        
        // Sensus Page
        composable(route = RailSensusScreen.SensusPage.route) {
            SensusPage(
                onBackClick = {
                    navController.navigate(RailSensusScreen.Dashboard.route) {
                        popUpTo(RailSensusScreen.Dashboard.route) { inclusive = true }
                    }
                },
                onItemClick = { sensusId ->
                    navController.navigate(RailSensusScreen.SensusDetail.createRoute(sensusId))
                },
                onBottomNavClick = { index ->
                    handleBottomNavigation(navController, index)
                }
            )
        }
        
        // Sensus Detail
        composable(
            route = RailSensusScreen.SensusDetail.route,
            arguments = listOf(
                navArgument("sensusId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val sensusId = backStackEntry.arguments?.getInt("sensusId") ?: 0
            SensusDetailPage(
                sensusId = sensusId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        
        // Loko / Sarana Page
        composable(route = RailSensusScreen.LokoPage.route) {
            LokoPage(
                onBackClick = {
                    navController.navigate(RailSensusScreen.Dashboard.route) {
                        popUpTo(RailSensusScreen.Dashboard.route) { inclusive = true }
                    }
                },
                onItemClick = { lokoId ->
                    navController.navigate(RailSensusScreen.LokoDetail.createRoute(lokoId))
                },
                onBottomNavClick = { index ->
                    handleBottomNavigation(navController, index)
                }
            )
        }
        
        // Loko Detail
        composable(
            route = RailSensusScreen.LokoDetail.route,
            arguments = listOf(
                navArgument("lokoId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val lokoId = backStackEntry.arguments?.getInt("lokoId") ?: 0
            LokoDetailPage(
                lokoId = lokoId,
                onBackClick = {
                    navController.popBackStack()
                },
                onDeleteClick = {
                    navController.popBackStack()
                }
            )
        }
        
        // User Page
        composable(route = RailSensusScreen.UserPage.route) {
            UserPage(
                onBackClick = {
                    navController.navigate(RailSensusScreen.Dashboard.route) {
                        popUpTo(RailSensusScreen.Dashboard.route) { inclusive = true }
                    }
                },
                onItemClick = { userId ->
                    // TODO: Navigate to user detail when created
                    // navController.navigate("userDetail/$userId")
                },
                onBottomNavClick = { index ->
                    handleBottomNavigation(navController, index)
                }
            )
        }
    }
}

/**
 * Helper function untuk bottom navigation
 */
private fun handleBottomNavigation(navController: NavHostController, index: Int) {
    when (index) {
        0 -> {
            navController.navigate(RailSensusScreen.Dashboard.route) {
                popUpTo(RailSensusScreen.Dashboard.route) { inclusive = true }
            }
        }
        1 -> {
            navController.navigate(RailSensusScreen.LokoPage.route) {
                popUpTo(RailSensusScreen.Dashboard.route) { inclusive = false }
            }
        }
        2 -> {
            navController.navigate(RailSensusScreen.SensusPage.route) {
                popUpTo(RailSensusScreen.Dashboard.route) { inclusive = false }
            }
        }
        3 -> {
            navController.navigate(RailSensusScreen.UserPage.route) {
                popUpTo(RailSensusScreen.Dashboard.route) { inclusive = false }
            }
        }
    }
}

/**
 * Extension functions untuk simplifikasi navigasi
 */
fun NavHostController.navigateToLogin() {
    navigate(RailSensusScreen.Login.route)
}

fun NavHostController.navigateToRegister() {
    navigate(RailSensusScreen.Register.route)
}

fun NavHostController.navigateToLanding() {
    navigate(RailSensusScreen.Landing.route) {
        popUpTo(0) { inclusive = true }
    }
}

fun NavHostController.navigateToDashboard() {
    navigate(RailSensusScreen.Dashboard.route) {
        popUpTo(RailSensusScreen.Landing.route) { inclusive = true }
    }
}

fun NavHostController.navigateToSensusPage() {
    navigate(RailSensusScreen.SensusPage.route)
}

fun NavHostController.navigateToLokoPage() {
    navigate(RailSensusScreen.LokoPage.route)
}

fun NavHostController.navigateToUserPage() {
    navigate(RailSensusScreen.UserPage.route)
}
