package com.sangjin.englishmaninkorea

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BottomNavigationViewModel : ViewModel(){

    private var _fragmentStatus = MutableLiveData<Int>()
    val fragmentStatus : LiveData<Int>
        get() = _fragmentStatus

    init {
        _fragmentStatus.value = 0
    }

    fun updateFragmentStatus(num: Int){
        _fragmentStatus.value = num
    }

}