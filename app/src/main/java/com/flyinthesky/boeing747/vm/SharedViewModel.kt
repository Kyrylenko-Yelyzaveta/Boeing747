package com.flyinthesky.boeing747.vm

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flyinthesky.boeing747.dto.BoeingItem

class SharedViewModel : ViewModel() {
    private var _itemsList = MutableLiveData<List<BoeingItem>>()
    val itemsList = _itemsList


    init {
        itemsList.postValue(
            mutableListOf(
                BoeingItem(),
                BoeingItem(),
                BoeingItem(),
                BoeingItem(),
            )
        )
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun addItem(item: BoeingItem) {
        val itemListCurrent: List<BoeingItem>? = _itemsList.value
        val newList = itemListCurrent?.toMutableList()
        newList?.add(item)
        _itemsList.postValue(newList)
    }
}
