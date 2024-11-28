package com.mwafaimk.unify.core.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.mwafaimk.unify.core.util.datastore.AuthStateManager
import com.mwafaimk.unify.core.util.datastore.DataStoreManager
import com.mwafaimk.unify.core.util.datastore.DataManager
import com.mwafaimk.unify.data.network.ApiService
import com.mwafaimk.unify.data.repository.network.NetworkRepositoryImpl
import com.mwafaimk.unify.data.repository.network.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://cherry-fish-earl.glitch.me/") // Replace with your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkRepository(apiService: ApiService): NetworkRepository {
        return NetworkRepositoryImpl(apiService)
    }


    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(dataStore: DataStore<Preferences>): DataStoreManager {
        return DataStoreManager(dataStore)
    }

    @Singleton
    @Provides
    fun provideApplicationContext(app: Application): Context {
        return app.applicationContext
    }

    @Singleton
    @Provides
    fun provideAuthStateManager(dataStore: DataStore<Preferences>): AuthStateManager {
        return AuthStateManager(dataStore)
    }


    @Singleton
    @Provides
    fun provideDataManager(dataStoreManager: DataStoreManager): DataManager {
        return DataManager(dataStoreManager = dataStoreManager)
    }
}





//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Provides
//    fun provideResources(application: Application): Resources {
//        return application.resources
//    }
//
//    @Provides
//    @Singleton
//    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
//        return context.dataStore
//    }
//
//    @Provides
//    @Singleton
//    fun provideDataStoreManager(dataStore: DataStore<Preferences>): DataStoreManager {
//        return DataStoreManager(dataStore)
//    }
//
//    @Singleton
//    @Provides
//    fun provideApplicationContext(app: Application): Context {
//        return app.applicationContext
//    }
//
//    @Singleton
//    @Provides
//    fun provideAuthStateManager(dataStore: DataStore<Preferences>): AuthStateManager {
//        return AuthStateManager(dataStore)
//    }
//
//    @Singleton
//    @Provides
//    fun provideNetworkRepository(apiService: ApiService, authStateManager: AuthStateManager): NetworkRepository {
//        return NetworkRepositoryImpl(apiService = apiService, authStateManager = authStateManager)
//    }
//
//    @Singleton
//    @Provides
//    fun provideMCDataManager(dataStoreManager: DataStoreManager): DataManager {
//        return DataManager(dataStoreManager = dataStoreManager)
//    }
//}