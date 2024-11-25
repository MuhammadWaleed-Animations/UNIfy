package com.mwafaimk.unify.data.network.apiServices

import com.mwafaimk.unify.data.model.admin.AdminDetails
import com.mwafaimk.unify.data.model.blockedUser.BlockUserRequest
import com.mwafaimk.unify.data.model.blockedUser.BlockUserResponse
import com.mwafaimk.unify.data.model.blockedUser.RemoveBlockedUserRequest
import com.mwafaimk.unify.data.model.blockedUser.RemoveBlockedUserResponse
import com.mwafaimk.unify.data.model.blockedUser.GetAllBlockedUsersResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface BlockedUserApiService {
    // Blocked User APIs
    @POST("/blocked-users/add")
    suspend fun addBlockedUser(@Body request: BlockUserRequest): BlockUserResponse
    @POST("/blocked-users/remove")
    suspend fun removeBlockedUser(@Body request: RemoveBlockedUserRequest): RemoveBlockedUserResponse
    @POST("/blocked-users")
    suspend fun getAllBlockedUsers(@Body request: AdminDetails): GetAllBlockedUsersResponse
}