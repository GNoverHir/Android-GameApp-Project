package br.com.gamesproject.data.api

import br.com.gamesproject.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Para converter JSON
            .build()

        retrofit.create(ApiService::class.java)

    }
}