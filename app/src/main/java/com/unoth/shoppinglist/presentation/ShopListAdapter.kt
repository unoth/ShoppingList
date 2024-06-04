package com.unoth.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.unoth.shoppinglist.R
import com.unoth.shoppinglist.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_shop_enabled,
            parent,
            false
        )

        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]
        val status = if (shopItem.enabled) {
            "Active"
        } else {
            "Not Active"
        }
        viewHolder.tvName.text = "${shopItem.name} $status"
        viewHolder.tvCount.text = shopItem.count.toString()
        viewHolder.view.setOnLongClickListener {
            // TODO
            true
        }
        if (shopItem.enabled) {
            viewHolder.tvName.setTextColor(
                ContextCompat.getColor(
                    viewHolder.view.context,
                    android.R.color.holo_red_light
                )
            )
        } else {
            viewHolder.tvName.setTextColor(
                ContextCompat.getColor(
                    viewHolder.view.context,
                    android.R.color.white
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

}