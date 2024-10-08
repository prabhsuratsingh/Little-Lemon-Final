package com.example.littlelemonfinal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuData(
    @SerialName("menu")
    val menuItems: List<MenuItemData>
)

@Serializable
data class MenuItemData(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
) {
    fun toMenuItemRoom() = MenuItem(
        id = id,
        title = title,
        price = price.toDouble(),
        desc = description,
        image = image,
        category = category
    )
}