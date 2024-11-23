package com.mwafaimk.unify.data.repository.network
//
//
//
//import com.mwafaimk.unify.core.util.datastore.AuthStateManager
//import com.mwafaimk.unify.data.network.ApiService
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object NetworkModule {
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://cherry-fish-earl.glitch.me/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideApiService(retrofit: Retrofit): ApiService {
//        return retrofit.create(ApiService::class.java)
//    }
//
//
//    @Singleton
//    @Provides
//    fun provideNetworkRepository(apiService: ApiService, authStateManager: AuthStateManager): NetworkRepository {
//        return NetworkRepositoryImpl(apiService = apiService, authStateManager = authStateManager)
//    }
//}