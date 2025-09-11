package com.dev.thriftswap.presentation.screens.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ProductDetailScreen(navController: NavController, categoryId : String){
    Text("Detail Screen + $categoryId")
}