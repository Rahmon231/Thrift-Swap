package com.dev.thriftswap.navigation

import java.lang.IllegalArgumentException

enum class ThriftScreens {
    SplashScreen,
    OnBoardingScreen,
    SignupScreen,
    HomeScreen;
    companion object {
        fun fromRoute(route: String?): ThriftScreens
        = when (route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            OnBoardingScreen.name -> OnBoardingScreen
            SignupScreen.name -> SignupScreen
            HomeScreen.name -> HomeScreen

            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }

    }
}