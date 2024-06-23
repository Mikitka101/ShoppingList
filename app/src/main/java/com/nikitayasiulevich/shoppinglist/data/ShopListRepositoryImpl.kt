package com.nikitayasiulevich.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nikitayasiulevich.shoppinglist.domain.ShopItem
import com.nikitayasiulevich.shoppinglist.domain.ShopListRepository
import kotlin.random.Random

object ShopListRepositoryImpl : ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private val shopList = sortedSetOf<ShopItem>({ o1, o2 ->
        o1.id.compareTo(o2.id)
    })

    init {
        for (i in 0 until 30) {
            val item = ShopItem("Name $i", i, Random.nextBoolean())
            addShopItem(item)
        }
    }

    private var autoIncrementId = 0
    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItemId: Int) {
        shopList.removeIf { it.id == shopItemId }
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        deleteShopItem(shopItem.id)
        addShopItem(shopItem)
    }

    override fun getShopItemList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    private fun updateList() {
        shopListLD.postValue(shopList.toList())
    }
}