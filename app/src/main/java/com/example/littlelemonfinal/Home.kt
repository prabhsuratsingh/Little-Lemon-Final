package com.example.littlelemonfinal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun HomePage(navController: NavHostController, database: LittleLemonDatabase) {
    val menuItemsDatabase by database.menuItemDao().getAllItems().observeAsState(emptyList())


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Spacer(modifier = Modifier.weight(0.5f))
            Spacer(modifier = Modifier.width(50.dp))
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    .height(50.dp)
                    .width(200.dp)
            )
            Spacer(modifier = Modifier.weight(0.5f))
            Icon(
                Icons.Default.Person,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .padding(end = 15.dp)
                    .size(50.dp)
                    .clip(CircleShape)
                    .clickable { navController.navigate(Profile.route) },
                tint = Color.Black
            )
        }
        HeroSection(menuItemsDatabase)


    }
}

@Composable
fun HeroSection(menuItemsDatabase: List<MenuItem>) {
    var menuItems = menuItemsDatabase
    var selectedCategory by remember { mutableStateOf("") }
    var searchPhrase by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF4C5E57))
            .padding(16.dp),
    ) {
        Text(
            text = "Little Lemon",
            color = Color(0xFFEECF00),
            fontSize = 45.sp,
            fontWeight = FontWeight.SemiBold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        ) {
            Column(modifier = Modifier.weight(0.6f)) {
                Text(
                    text = "Chicago", color = Color.White, fontSize = 30.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                    color = Color.White,
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            Image(
                painter = painterResource(id = R.drawable.chef),
                contentDescription = "Sandwich",
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
            )
        }

        TextField(
            value = searchPhrase,
            onValueChange = {searchPhrase = it},
            label = { Text("Search") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null)
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFEAEAEA)),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )

        if (searchPhrase.isNotEmpty()) {
            menuItems =
                menuItemsDatabase.filter { it.title.contains(searchPhrase, ignoreCase = true) }
        }
    }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "ORDER FOR DELIVERY!",
            modifier = Modifier.padding(top = 30.dp),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 18.sp
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp)
        ) {
            Button(
                onClick = {
                    selectedCategory = "starters"
                }, modifier = Modifier.height(40.dp), colors = ButtonDefaults.buttonColors(
                    Color(0xFFEDEFEE)
                ), shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Starters", fontWeight = FontWeight.Bold, color = Color(0xFF4C5E57))
            }

            Button(
                onClick = {
                    selectedCategory = "mains"
                }, modifier = Modifier.height(40.dp), colors = ButtonDefaults.buttonColors(
                    Color(0xFFEDEFEE)
                ), shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Mains", fontWeight = FontWeight.Bold, color = Color(0xFF4C5E57))
            }

            Button(
                onClick = {
                    selectedCategory = "desserts"
                }, modifier = Modifier.height(40.dp), colors = ButtonDefaults.buttonColors(
                    Color(0xFFEDEFEE)
                ), shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Desserts", fontWeight = FontWeight.Bold, color = Color(0xFF4C5E57))
            }

            Button(
                onClick = {
                    selectedCategory = "drinks"
                }, modifier = Modifier.height(40.dp), colors = ButtonDefaults.buttonColors(
                     Color(0xFFEDEFEE)
                ), shape = RoundedCornerShape(15.dp)
            ) {
                Text(text = "Drinks", fontWeight = FontWeight.Bold, color = Color(0xFF4C5E57))
            }
        }
    }
    if (selectedCategory.isNotEmpty()) {
        menuItems = menuItems.filter { it.category.contains(selectedCategory) }
    }
    MenuItems(menuItemsList = menuItems)
}


@Composable
fun MenuItems(menuItemsList: List<MenuItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        items(items = menuItemsList, itemContent = { menuItem -> MenuItem(menuItem) })
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(menuItem: MenuItem) {
    Divider(
        thickness = 2.dp, color = Color(0xFFDDDDDD), modifier = Modifier.padding(vertical = 15.dp)
    )
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        Text(text = menuItem.title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
        Row(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = menuItem.desc, fontSize = 13.sp, color = Color.Gray)
                Text(
                    text = "$${menuItem.price}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = Color.Gray
                )
            }

            GlideImage(
                model = menuItem.image,
                contentDescription = "Menu Item Image",
                Modifier
                    .padding(start = 15.dp)
                    .size(100.dp),
                contentScale = ContentScale.Crop,
            )
        }
    }
}
