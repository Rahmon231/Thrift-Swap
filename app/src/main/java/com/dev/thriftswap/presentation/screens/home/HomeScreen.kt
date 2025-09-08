package com.dev.thriftswap.presentation.screens.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dev.thriftswap.presentation.components.InputField
import com.dev.thriftswap.presentation.components.ThriftAppBar
import com.dev.thriftswap.presentation.navigation.ThriftScreens
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter

@Composable
fun HomeScreen(navController: NavController){
    val context = LocalContext.current
    Scaffold(
        topBar = {
            ThriftAppBar(
                title = "Home",
                actionIcon = Icons.Default.Person,
                onActionClicked = {
                    Toast.makeText(context, "Action Clicked", Toast.LENGTH_SHORT).show()
                },
                navIcon = Icons.Default.Menu,
                onBackArrowClicked = {
                    Toast.makeText(context, "Back Clicked", Toast.LENGTH_SHORT).show()
                }
            )
        }
    ) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            Column(modifier = Modifier.padding(4.dp)) {
                SearchForm(modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                    navController = navController) { searchItem->
                    Log.d("Search Item", "HomeScreen: $searchItem")
                }
            }
        }
    }
}

@OptIn(FlowPreview::class)
@Composable
fun SearchForm(modifier: Modifier = Modifier,
               navController: NavController,
               onSearch: (String) -> Unit = {}){
    Column() {
        val searchQueryState = rememberSaveable { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current
        val valid = remember(searchQueryState.value) {
            searchQueryState.value.trim().isNotEmpty()
        }
        InputField(valueState = searchQueryState,
            labelId = "Search",
            enabled = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White
            ),
            leadingIcon = Icons.Default.Search,
            onValueChange = { newValue ->
                searchQueryState.value = newValue
            },
            onAction =  KeyboardActions {
                if (!valid) return@KeyboardActions
                //onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()
            },
            onFilterClick = {
                navController.navigate(ThriftScreens.FilterScreen.name)
            })
        // Debounced search
        LaunchedEffect(searchQueryState.value) {
            snapshotFlow { searchQueryState.value.trim() }
                .debounce(500) // wait 500ms after typing stops
                .filter { it.isNotEmpty() }
                .collect { query ->
                    onSearch(query) // trigger search
                }
        }
    }
}