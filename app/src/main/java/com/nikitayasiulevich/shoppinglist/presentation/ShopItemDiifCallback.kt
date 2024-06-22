package com.nikitayasiulevich.shoppinglist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.nikitayasiulevich.shoppinglist.domain.ShopItem

class ShopItemDiifCallback: DiffUtil.ItemCallback<ShopItem>(){
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }

}