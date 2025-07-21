package com.unoth.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unoth.shoppinglist.domain.EditShopItemUseCase
import com.unoth.shoppinglist.domain.GetShopListUseCase
import com.unoth.shoppinglist.domain.RemoveShopItemUseCase
import com.unoth.shoppinglist.domain.ShopItem
import javax.inject.Inject
import kotlinx.coroutines.launch

class MainViewModel @Inject constructor(
    private val getShopListUseCase: GetShopListUseCase,
    private val removeShopItemUseCase: RemoveShopItemUseCase,
    private val editShopItemUseCase: EditShopItemUseCase
) : ViewModel() {

    val shopList = getShopListUseCase.getShopList()

    fun removeShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            removeShopItemUseCase.removeShopItem(shopItem)
        }
    }

    fun changeEnableState(shopItem: ShopItem) {
        viewModelScope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }
    }
}