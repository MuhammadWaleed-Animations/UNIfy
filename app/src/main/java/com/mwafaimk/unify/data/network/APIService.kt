package com.mwafaimk.unify.data.network

import com.mwafaimk.unify.data.network.apiServices.BlockedUserApiService
import com.mwafaimk.unify.data.network.apiServices.PostApiService
import com.mwafaimk.unify.data.network.apiServices.UserApiService


interface ApiService:UserApiService,PostApiService,BlockedUserApiService {
//
}