package com.dev.thriftswap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dev.thriftswap.presentation.navigation.ThriftNavigation
import com.dev.thriftswap.ui.theme.ThriftSwapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThriftSwapTheme {
                ThriftApp()
            }
        }
    }
}
@Composable
fun ThriftApp() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ThriftNavigation()
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ThriftSwapTheme {

    }
}