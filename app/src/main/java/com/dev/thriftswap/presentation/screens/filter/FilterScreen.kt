package com.dev.thriftswap.presentation.screens.filter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dev.thriftswap.R
import com.dev.thriftswap.presentation.components.ThriftAppBar

@Composable
fun FilterScreen(navController: NavController,
                 filterScreenViewModel: FilterScreenViewModel = hiltViewModel()){
    Scaffold(
        topBar = {
            ThriftAppBar(
                title = "Filter",
                actionIcon = Icons.Default.Delete,
                navIcon = Icons.Default.Close,
                onActionClicked = {/*TODO: clear filter*/},
            ) {
                navController.popBackStack()
            }
        }
    ){
        Surface(modifier = Modifier.fillMaxSize().padding(it)) {
            FilterScreenContent()
        }
    }

}

@Composable
fun FilterScreenContent() {
    var selectedGender by remember { mutableStateOf("Men") }
    Column(
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Gender",
            style = TextStyle(
                color = Color(0xFF3D405B),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.roboto)))
        )
        GenderSelectRow(selectedGender = selectedGender){
            selectedGender = it
        }
    }
}

@Composable
fun GenderSelectRow(selectedGender: String = "Men",
                    onGenderSelected: (String) -> Unit) {
    val genders = listOf("Men", "Women", "Unisex")
    Row(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        genders.forEach { gender ->
            val isSelected = gender == selectedGender
            Button(
                onClick = { onGenderSelected(gender) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) Color(0xFFCCDCD7) else Color.Transparent,
                    contentColor = if (isSelected) Color(0xFF446B5E) else Color.Gray
                ),
                shape = RoundedCornerShape(6.dp),
                border = if (isSelected) null else BorderStroke(1.dp, Color.LightGray),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Text(text = gender)
            }
        }
        }

}