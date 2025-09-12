package com.dev.thriftswap.presentation.screens.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dev.thriftswap.presentation.components.ThriftAppBar
import com.dev.thriftswap.presentation.screens.home.HomeViewModel

@Composable
fun ProductDetailScreen(navController: NavController,
                        categoryId : String,
                        homeViewModel: HomeViewModel = hiltViewModel()){
    val category = homeViewModel.getCategory(categoryId)
    Scaffold(
        topBar = {
            ThriftAppBar(
                title = category?.name ?: "Invalid",
                navIcon = Icons.AutoMirrored.Filled.ArrowBack,
                actionIcon = Icons.Default.ShoppingCart,
                onActionClicked = {/*TODO: Add Item TO Cart*/}
            ) {
                navController.popBackStack()
            }
        }
    ) {
        Surface (modifier = Modifier.padding(it)){

        }
    }
}