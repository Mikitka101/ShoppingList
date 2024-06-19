package com.nikitayasiulevich.shoppinglist.data

import com.nikitayasiulevich.shoppinglist.domain.ShopItem
import com.nikitayasiulevich.shoppinglist.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0
    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItemId: Int) {
        shopList.removeIf { it.id == shopItemId }
    }

    override fun editShopItem(shopItem: ShopItem) {
        deleteShopItem(shopItem.id)
        addShopItem(shopItem)
    }

    override fun getShopItemList(): List<ShopItem> {
        return shopList.toList()
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }
}