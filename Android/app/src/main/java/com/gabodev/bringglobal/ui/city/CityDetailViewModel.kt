package com.gabodev.bringglobal.ui.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.MutableLiveData
import com.gabodev.bringglobal.utils.Resource
import androidx.hilt.lifecycle.ViewModelInject
import com.gabodev.bringglobal.data.entities.City
import com.gabodev.bringglobal.data.repository.CityRepository

class CityDetailViewModel @ViewModelInject constructor(
    private val repository: CityRepository
) : ViewModel() {

    private val _name = MutableLiveData<String>()
    private val _units = MutableLiveData<String>()
    private val _appid = MutableLiveData<String>()

    private val _city = _name.switchMap { name ->
        _units.value?.let { _appid.value?.let {
                it1 -> repository.getCityByName(name, it, it1)
            }
        }!!
    }
    val city: LiveData<Resource<City>> = _city

    fun start(name: String, units: String?, appid: String?) {
        _name.value = name
        _units.value = units
        _appid.value = appid
    }
}