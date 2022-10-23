package com.udacity.shoestore.ui.shoes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.data.model.Shoe

class ShoesViewModel : ViewModel() {

    private var _shoeList: MutableLiveData<List<Shoe>> = MutableLiveData(mutableListOf())
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    fun addShoe(shoe: Shoe) {
        _shoeList.postValue(shoeList.value?.plus(shoe))
    }
}