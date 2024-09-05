package com.unoth.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    fun getShopList(): LiveData<List<ShopItem>>
    suspend fun addShopItem(shopItem: ShopItem)
    suspend fun editShopItem(shopItem: ShopItem)
    suspend fun removeShopItem(shopItem: ShopItem)
    suspend fun getShopItemId(shopItemId: Int): ShopItem
}