package com.ashwin.android.caching.restaurants

import androidx.lifecycle.*
import com.ashwin.android.caching.data.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    restaurantRepository: RestaurantRepository
//    api: RestaurantApi
) : ViewModel() {
    val restaurantsLiveData = restaurantRepository.getRestaurants().asLiveData()

//    private val _restaurantsLiveData = MutableLiveData<List<Restaurant>>()
//    val restaurantsLiveData: LiveData<List<Restaurant>> = _restaurantsLiveData
//
//    init {
//        viewModelScope.launch {
//            val restaurants = api.getRestaurants()
//            delay(2000L)
//            _restaurantsLiveData.value = restaurants
//        }
//    }
}
