package com.dev.thriftswap.presentation.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.thriftswap.presentation.screens.home.HomeScreen
import com.dev.thriftswap.presentation.screens.onboard.OnboardingScreen
import com.dev.thriftswap.presentation.screens.register.RegisterScreen
import com.dev.thriftswap.presentation.screens.splash.ThriftSplashScreen


@Composable
fun ThriftNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ThriftScreens.SplashScreen.name) {
        composable(ThriftScreens.SplashScreen.name) {
            ThriftSplashScreen(navController = navController)
        }
        composable(ThriftScreens.OnBoardingScreen.name) {
            OnboardingScreen(navController = navController)
        }
        composable(ThriftScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(ThriftScreens.RegisterScreen.name) {
            RegisterScreen(navController = navController)
        }
        composable(ThriftScreens.FilterScreen.name) {
            FilterScreen(navController = navController)
        }
    }
}