package com.unoth.shoppinglist.domain

class GetShopItemIdUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun getShopItemId(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItemId(shopItemId)
    }
}