package com.sangjin.englishmaninkorea.englishlearn

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sangjin.englishmaninkorea.database.DB
import com.sangjin.englishmaninkorea.englishlearn.data.model.Learn
import com.sangjin.englishmaninkorea.englishlearn.data.repository.LearnDataRepositoryImpl
import com.sangjin.englishmaninkorea.englishlearn.data.source.local.LocalDataSourceImpl
import com.sangjin.englishmaninkorea.englishlearn.data.source.remote.RemoteDataSourceImpl
import kotlinx.android.synthetic.main.fragment_english_learn.view.*
class EnglishLearnViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application
    private val learnDataRepositoryImpl = LearnDataRepositoryImpl(RemoteDataSourceImpl(), LocalDataSourceImpl())

    var learnList = MutableLiveData<List<Learn>>()

    init{
        getContent()
    }

    private fun getContent(){

        learnDataRepositoryImpl.getEnglishContent(
            DB.getInstance(context),
            onSuccess = { it ->
                learnList.value = it
                Log.d("#### isis : ", "onSuccess")
                Log.d("#### LiveData: ", learnList.value.toString())

                //view.progressbar_loading.hide()
            },
            onFailure = {
                //view.progressbar_loading.hide()
                Log.d("#### isis : ", "onFailure")
            }
        )

    }



}

