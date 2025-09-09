package com.dev.thriftswap.presentation.screens.filter

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.clip
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
import com.dev.thriftswap.utils.Constants.GENDER_OPTIONS
import com.dev.thriftswap.utils.Constants.SIZE_OPTIONS

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
    var selectedSize by remember { mutableStateOf("L") }

    Column(
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        verticalArrangement = Arrangement.Top
    ) {
        FilterScreenHeadings(title = "Gender")
        SelectableRow(
            options = GENDER_OPTIONS,
            selectedOption = selectedGender,
            onOptionSelected = { selectedGender = it },
            buttonModifier = Modifier
                .weight(1f)
                .height(48.dp),
            maxItemsInEachRow = Int.MAX_VALUE // single row
        )

        FilterScreenHeadings(title = "Size")
        SelectableRow(
            options = SIZE_OPTIONS,
            selectedOption = selectedSize,
            onOptionSelected = { selectedSize = it },
            buttonModifier = Modifier
                .width(96.dp)
                .height(40.dp),
            textStyle = TextStyle(fontSize = 14.sp),
            maxItemsInEachRow = 4 // 4 items per row
        )

    }
}

@Composable
fun FilterScreenHeadings(title: String) {
    Text(text = title,
        style = TextStyle(
            color = Color(0xFF3D405B),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily(Font(R.font.roboto))),
        modifier = Modifier.padding(12.dp)
    )
}

@Composable
fun SelectableRow(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    buttonModifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(fontSize = 16.sp),
    maxItemsInEachRow: Int = Int.MAX_VALUE // 👈 configurable per use case
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        maxItemsInEachRow = maxItemsInEachRow
    ) {
        options.forEach { option ->
            val isSelected = option == selectedOption
            Button(
                onClick = { onOptionSelected(option) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) Color(0xFFD0E1DB) else Color.Transparent,
                    contentColor = if (isSelected) Color(0xFF1B4332) else Color.Gray
                ),
                shape = RoundedCornerShape(6.dp),
                border = if (isSelected) null else BorderStroke(1.dp, Color.LightGray),
                elevation = ButtonDefaults.buttonElevation(0.dp),
                modifier = buttonModifier
            ) {
                Text(text = option, style = textStyle)
            }
        }
    }
}


