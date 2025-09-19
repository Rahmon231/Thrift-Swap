package com.dev.thriftswap.presentation.navigation

import java.lang.IllegalArgumentException

enum class ThriftScreens {
    SplashScreen,
    OnBoardingScreen,

    RegisterScreen,
    FilterScreen,
    ProductDetailScreen,
    CartScreen,
    SellScreen,
    WishListScreen,
    ProfileScreen,
    HomeScreen;
    companion object {
        fun fromRoute(route: String?): ThriftScreens
        = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            OnBoardingScreen.name -> OnBoardingScreen
            RegisterScreen.name -> RegisterScreen
            HomeScreen.name -> HomeScreen
            FilterScreen.name-> FilterScreen
            ProductDetailScreen.name -> ProductDetailScreen
            CartScreen.name -> CartScreen
            SellScreen.name -> SellScreen
            WishListScreen.name -> WishListScreen
            ProfileScreen.name -> ProfileScreen

            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }

    }
}