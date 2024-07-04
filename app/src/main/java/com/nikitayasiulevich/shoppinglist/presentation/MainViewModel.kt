package com.nikitayasiulevich.shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nikitayasiulevich.shoppinglist.data.ShopListRepositoryImpl
import com.nikitayasiulevich.shoppinglist.domain.DeleteShopItemUseCase
import com.nikitayasiulevich.shoppinglist.domain.EditShopItemUseCase
import com.nikitayasiulevich.shoppinglist.domain.GetShopItemListUseCase
import com.nikitayasiulevich.shoppinglist.domain.ShopItem
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopItemListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopItemList()

    fun deleteShopItem(shopItemId: Int) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItemId)
        }
    }

    fun changeEnableState(shopItem: ShopItem) {
        viewModelScope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }
    }
}