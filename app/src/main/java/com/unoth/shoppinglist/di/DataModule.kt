package com.unoth.shoppinglist.di

import android.app.Application
import com.unoth.shoppinglist.data.AppDatabase
import com.unoth.shoppinglist.data.ShopListDao
import com.unoth.shoppinglist.data.ShopListRepositoryImpl
import com.unoth.shoppinglist.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideShopListDao(application: Application): ShopListDao {
            return AppDatabase.getInstance(application).shopListDao()
        }
    }
}