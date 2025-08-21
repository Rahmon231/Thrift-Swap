package com.dev.thriftswap.presentation.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun ThriftNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ThriftScreens.SplashScreen.name) {
        composable(ThriftScreens.SplashScreen.name) {
            //ThriftSplashScreen(navController = navController)
        }
    }
}