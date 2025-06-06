package com.unoth.shoppinglist.di

import androidx.lifecycle.ViewModel
import com.unoth.shoppinglist.presentation.MainViewModel
import com.unoth.shoppinglist.presentation.ShopItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModelModule: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShopItemViewModel::class)
    fun bindShopItemViewModel(viewModelModule: ShopItemViewModel): ViewModel
}