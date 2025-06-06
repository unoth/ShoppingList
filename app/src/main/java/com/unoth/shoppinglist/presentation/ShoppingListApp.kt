package com.unoth.shoppinglist.presentation

import android.app.Application
import com.unoth.shoppinglist.di.DaggerApplicationComponent

class ShoppingListApp : Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}