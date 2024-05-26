package com.unoth.shoppinglist.domain

interface ShopListRepository {
    fun getShopList(): List<ShopItem>
    fun addShopItem(shopItem: ShopItem)
    fun editShopItem(shopItem: ShopItem)
    fun removeShopItem(shopItem: ShopItem)
    fun getShopItemId(shopItemId: Int): ShopItem
}