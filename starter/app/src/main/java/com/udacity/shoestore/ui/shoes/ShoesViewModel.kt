package com.udacity.shoestore.ui.shoes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.data.model.Shoe

class ShoesViewModel : ViewModel() {

    private val _shoeList: MutableLiveData<List<Shoe>> = MutableLiveData(mutableListOf())
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    private val _shoeDetailModel: MutableLiveData<Shoe> by lazy {
        MutableLiveData(Shoe())
    }
    val shoeDetailModel: LiveData<Shoe>
        get() = _shoeDetailModel


    fun addShoe() {
        val newShoe = shoeDetailModel.value
        newShoe?.let {
            _shoeList.postValue(shoeList.value?.plus(newShoe))
            _shoeDetailModel.value = Shoe()
        }
    }

    fun clearShoeDetailModel() {
        _shoeDetailModel.value = Shoe()
    }
}