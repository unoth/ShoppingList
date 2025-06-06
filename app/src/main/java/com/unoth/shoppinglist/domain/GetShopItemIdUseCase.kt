package com.unoth.shoppinglist.domain

import javax.inject.Inject

class GetShopItemIdUseCase @Inject constructor(
    private val shopListRepository: ShopListRepository
) {
    suspend fun getShopItemId(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItemId(shopItemId)
    }
}