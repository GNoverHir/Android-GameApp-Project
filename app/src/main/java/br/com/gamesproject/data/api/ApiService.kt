package br.com.gamesproject.data.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class User(val nickname: String?, val email: String, val password: String, val url: String?)


interface ApiService {

    @POST("user")
    fun register(@Body user: User): Call<User>

    @POST("user/login")
    fun login(@Body user: User): Call<User>

    @GET("user/{email}")
    fun getProfile(@Path("email") email: String): Call<User>

}