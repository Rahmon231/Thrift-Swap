package com.dev.thriftswap.presentation.navigation

import java.lang.IllegalArgumentException

enum class ThriftScreens {
    SplashScreen,
    OnBoardingScreen,

    RegisterScreen,
    FilterScreen,
    ProductDetailScreen,
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

            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }

    }
}