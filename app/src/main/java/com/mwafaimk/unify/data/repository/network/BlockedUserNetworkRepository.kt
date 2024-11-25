package com.mwafaimk.unify.data.repository.network

import com.mwafaimk.unify.data.model.admin.AdminDetails
import com.mwafaimk.unify.data.model.blockedUser.BlockUserRequest
import com.mwafaimk.unify.data.model.blockedUser.BlockUserResponse
import com.mwafaimk.unify.data.model.blockedUser.RemoveBlockedUserRequest
import com.mwafaimk.unify.data.model.blockedUser.RemoveBlockedUserResponse
import com.mwafaimk.unify.data.model.blockedUser.GetAllBlockedUsersResponse

interface BlockedUserNetworkRepository {
    suspend fun blockUser(request: BlockUserRequest): BlockUserResponse
    suspend fun removeBlockedUser(request: RemoveBlockedUserRequest): RemoveBlockedUserResponse
    suspend fun getAllBlockedUsers(request: AdminDetails):GetAllBlockedUsersResponse
}