package com.dev.thriftswap.presentation.screens.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.dev.thriftswap.R
import com.dev.thriftswap.data.model.RecommendedItem
import com.dev.thriftswap.presentation.components.InputField
import com.dev.thriftswap.presentation.components.ThriftAppBar
import com.dev.thriftswap.presentation.navigation.ThriftScreens
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter

@Composable
fun HomeScreen(navController: NavController,
               homeViewModel: HomeViewModel = hiltViewModel()
){
    val recommendedItems by homeViewModel.recommended.collectAsState()
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
                HomeTitleRow(title = "Category")
                CategoryRow(homeViewModel = homeViewModel){ categoryId ->
                    navController.navigate(ThriftScreens.ProductDetailScreen.name + "/$categoryId")
                }
                HomeTitleRow(title = "Recommended", subtitle = "show more"){
                    //TODO: Implement SHOW MORE functionality
                    Toast.makeText(context, "Show More Clicked", Toast.LENGTH_SHORT).show()
                }
                RecommendedList(items = recommendedItems) { selectedItem ->
                    // handle click of recommended item
                    Toast.makeText(context, "Item Clicked: ${selectedItem.name}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
@Composable
fun HomeTitleRow(title: String,
                 subtitle: String = "",
                 onShowMoreClick: () -> Unit = {}){
    Row(modifier = Modifier.fillMaxWidth().padding(top = 10.dp, start = 16.dp, end = 16.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Text(text = title,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.roboto))))
        Text(text = subtitle,
            fontWeight = FontWeight.W400,
            fontSize = 12.sp,
            color = Color(0xFF5B8E7D),
            fontFamily = FontFamily(Font(R.font.roboto)),
            modifier = Modifier.clickable { onShowMoreClick.invoke() })
            }
    }

@Composable
fun RecommendedItemCard(modifier:Modifier = Modifier,
                        imageModifier: Modifier = Modifier,
                        item: RecommendedItem,
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

            Text(
                text = item.category,
                fontWeight = FontWeight.Light,
                fontSize = 14.sp,
                maxLines = 1,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.Start),
                overflow = TextOverflow.Ellipsis
            )

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
fun RecommendedList(items: List<RecommendedItem>,
                    onItemClick: (RecommendedItem) -> Unit = {}) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement =Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(items) { item ->
            RecommendedItemCard(item = item) {
                onItemClick(item)
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

@Composable
fun CategoryRow(
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    onCategoryClick: (String) -> Unit = {}
) {
    val categories by homeViewModel.categories.collectAsState()
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
    ) {
        items(categories) { category ->
            Column(
                modifier = Modifier.width(80.dp).clickable{ onCategoryClick(category.categoryId) },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Circular image
                if (category.imageRes != null) {
                    Image(
                        painter = painterResource(id = category.imageRes),
                        contentDescription = category.name,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else if (category.imageUrl != null) {
                    AsyncImage(
                        model = category.imageUrl,
                        contentDescription = category.name,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Category name
                Text(
                    text = category.name,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    ),
                    maxLines = 1
                )
            }
        }
    }
}

