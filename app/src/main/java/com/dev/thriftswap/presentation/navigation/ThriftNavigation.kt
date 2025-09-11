package com.dev.thriftswap.presentation.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dev.thriftswap.presentation.screens.detail.ProductDetailScreen
import com.dev.thriftswap.presentation.screens.filter.FilterScreen
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
        composable(
            route = ThriftScreens.ProductDetailScreen.name + "/{categoryId}",
            arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
            ProductDetailScreen(navController = navController, categoryId = categoryId)
        }
    }
}