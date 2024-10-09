package com.example.littlelemonfinal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetworkData(
    @SerialName("menu")
    val menuItems: List<MenuItemData>
)

@Serializable
data class MenuItemData(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: Int,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String,
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