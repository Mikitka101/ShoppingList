package com.nikitayasiulevich.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.nikitayasiulevich.shoppinglist.data.ShopListRepositoryImpl
import com.nikitayasiulevich.shoppinglist.domain.DeleteShopItemUseCase
import com.nikitayasiulevich.shoppinglist.domain.EditShopItemUseCase
import com.nikitayasiulevich.shoppinglist.domain.GetShopItemListUseCase
import com.nikitayasiulevich.shoppinglist.domain.ShopItem

class MainViewModel() : ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopItemListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopItemList()

    fun deleteShopItem(shopItemId: Int) {
        deleteShopItemUseCase.deleteShopItem(shopItemId)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}