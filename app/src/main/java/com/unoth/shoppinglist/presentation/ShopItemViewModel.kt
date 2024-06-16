package com.unoth.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.unoth.shoppinglist.data.ShopListRepositoryImpl
import com.unoth.shoppinglist.domain.AddShopItemUseCase
import com.unoth.shoppinglist.domain.EditShopItemUseCase
import com.unoth.shoppinglist.domain.GetShopItemIdUseCase
import com.unoth.shoppinglist.domain.ShopItem

class ShopItemViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopItemIdUseCase = GetShopItemIdUseCase(repository)

    fun getShopItemId(shopItemId: Int) {
        val item = getShopItemIdUseCase.getShopItemId(shopItemId)
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val shopItem = ShopItem(name, count, true)
            editShopItemUseCase.editShopItem(shopItem)
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            // TODO: show error to user
            result = false
        }
        if (count <= 0) {
            // TODO: show error to user
            result = false
        }
        return result
    }
}