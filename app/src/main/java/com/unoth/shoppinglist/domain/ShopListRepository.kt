package com.unoth.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    fun getShopList(): LiveData<List<ShopItem>>
    fun addShopItem(shopItem: ShopItem)
    fun editShopItem(shopItem: ShopItem)
    fun removeShopItem(shopItem: ShopItem)
    fun getShopItemId(shopItemId: Int): ShopItem
}