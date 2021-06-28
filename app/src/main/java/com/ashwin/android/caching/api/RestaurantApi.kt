package com.ashwin.android.caching.api

import com.ashwin.android.caching.data.Restaurant
import retrofit2.http.GET

interface RestaurantApi {
    companion object {
        const val BASE_URL = "https://random-data-api.com/api/"
    }

    @GET("restaurant/random_restaurant?size=20")
    suspend fun getRestaurants(): List<Restaurant>
}
