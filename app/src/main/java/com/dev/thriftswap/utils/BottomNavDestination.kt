package com.dev.thriftswap.utils

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.dev.thriftswap.presentation.navigation.ThriftScreens

enum class BottomNavDestination(val route: String, val icon: ImageVector, val label: String) {
    Home(ThriftScreens.HomeScreen.name, Icons.Default.Home, "Home"),
    Cart(ThriftScreens.CartScreen.name, Icons.Default.ShoppingCart, "Cart"),
    Sell(ThriftScreens.SellScreen.name, Icons.Default.AddBox, "Sell"),
    Wishlist(ThriftScreens.WishListScreen.name, Icons.Default.FavoriteBorder, "Wishlist"),
    Profile(ThriftScreens.ProfileScreen.name, Icons.Default.Person, "Profile")
}
