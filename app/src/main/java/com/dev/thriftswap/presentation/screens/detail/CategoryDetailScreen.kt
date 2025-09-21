package com.dev.thriftswap.presentation.screens.detail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dev.thriftswap.data.model.CategoryItem
import com.dev.thriftswap.data.model.RecommendedItem
import com.dev.thriftswap.presentation.components.ThriftAppBar
import com.dev.thriftswap.presentation.screens.home.HomeViewModel
import com.dev.thriftswap.presentation.screens.home.RecommendedItemCard

@Composable
fun CategoryDetailScreen(navController: NavController,
                         categoryId : String,
                         homeViewModel: HomeViewModel = hiltViewModel()){
    val category = homeViewModel.getCategory(categoryId)
    val categoryItems = homeViewModel.getCategoriesItemById(categoryId)
    val context = LocalContext.current
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
            CategoryItemsList(
                items = categoryItems
            ){ item->
                Toast.makeText(context, "Item Clicked: ${item.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun CategoryItemCard(modifier:Modifier = Modifier,
                        imageModifier: Modifier = Modifier,
                        item: CategoryItem,
                        onClick: () -> Unit = {}) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .width(160.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = imageModifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))


            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = item.size,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "₦${item.price}",
                    color = Color(0xFF5B8E7D),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )

                Text(
                    text = item.grade,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

        }
    }
}


@Composable
fun CategoryItemsList(items: List<CategoryItem>,
                      onItemClick: (CategoryItem) -> Unit = {}){
    LazyColumn() {
        items(items = items){ item ->
            CategoryItemCard(
                modifier = Modifier
                    .fillMaxWidth(),
                imageModifier = Modifier.fillMaxWidth().height(273.dp),
                item = item,
                onClick = { onItemClick(item) }
            )

        }
    }
}