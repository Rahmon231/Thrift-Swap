package com.dev.thriftswap.presentation.navigation
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dev.thriftswap.presentation.components.BottomNavBar
import com.dev.thriftswap.presentation.screens.cart.CartScreen
import com.dev.thriftswap.presentation.screens.detail.ProductDetailScreen
import com.dev.thriftswap.presentation.screens.filter.FilterScreen
import com.dev.thriftswap.presentation.screens.home.HomeScreen
import com.dev.thriftswap.presentation.screens.onboard.OnboardingScreen
import com.dev.thriftswap.presentation.screens.profile.ProfileScreen
import com.dev.thriftswap.presentation.screens.register.RegisterScreen
import com.dev.thriftswap.presentation.screens.sell.SellScreen
import com.dev.thriftswap.presentation.screens.splash.ThriftSplashScreen
import com.dev.thriftswap.presentation.screens.wishlist.WishListScreen
import com.dev.thriftswap.utils.BottomNavDestination


@Composable
fun ThriftNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Decide when to show bottom nav
    val showBottomBar = BottomNavDestination.entries.any { it.route == currentRoute }
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(navController = navController,
            startDestination = ThriftScreens.SplashScreen.name,
            modifier = Modifier.padding(innerPadding)) {
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
            composable(ThriftScreens.CartScreen.name) {
                CartScreen(navController = navController)
            }
            composable(ThriftScreens.SellScreen.name) {
                SellScreen(navController = navController)
            }
            composable(ThriftScreens.WishListScreen.name) {
                WishListScreen(navController = navController)
            }
            composable(ThriftScreens.ProfileScreen.name) {
                ProfileScreen(navController = navController)
            }
        }
    }


}