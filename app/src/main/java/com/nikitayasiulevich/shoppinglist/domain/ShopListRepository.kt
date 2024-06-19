package com.nikitayasiulevich.shoppinglist.domain

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItemId: Int)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItemList(): List<ShopItem>

    fun getShopItem(shopItemId: Int): ShopItem
}