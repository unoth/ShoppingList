package com.unoth.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.unoth.shoppinglist.data.ShopListRepositoryImpl
import com.unoth.shoppinglist.domain.EditShopItemUseCase
import com.unoth.shoppinglist.domain.GetShopListUseCase
import com.unoth.shoppinglist.domain.RemoveShopItemUseCase
import com.unoth.shoppinglist.domain.ShopItem

class MainViewModel : ViewModel() {

    //for testing
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val removeShopItemUseCase = RemoveShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun removeShopItem(shopItem: ShopItem) {
        removeShopItemUseCase.removeShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}