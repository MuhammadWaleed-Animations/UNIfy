package com.mwafaimk.unify.data.network


import com.mwafaimk.unify.data.model.user.login.LoginRequest
import com.mwafaimk.unify.data.model.user.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST
//import retrofit2.http.POST

//data class LoginRequest(val email: String, val password: String)

//data class LoginResponse(val user: User, val isAdmin: Boolean)

//data class User(val username: String, val email: String, val contactInfo: String, val profilePicture: String?, val organization: String)

interface ApiService {
    //@HTTP(method = "GET", path = "/users/login", hasBody = true) // Use @HTTP instead of @GET

    @POST("/users/login")
    suspend fun loginUser(@Body request: LoginRequest): LoginResponse
}