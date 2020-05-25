package com.sangjin.englishmaninkorea.englishlearn.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.sangjin.englishmaninkorea.englishlearn.data.model.Learn
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RemoteDataSourceImpl() : RemoteDataSource {

    @SuppressLint("CheckResult")
    override fun getEnglishContent(
        onSuccess: (learnList: ArrayList<Learn>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
        val quota = QuotaApi.retrofitService.requestQuota().flatMap { Quota ->

            val learn =
                Learn(3)
            learn.qouta = Quota
            Observable.just(learn)
        }

        val news = NewsApi.retrofitService.requestNewsList().flatMap { newsResponse ->

            val learn =
                Learn(1)
            learn.newsList = newsResponse.articles

            Observable.just(learn)
        }

        quota.mergeWith(news)
            .toList()
            .map {
                val learnList = ArrayList<Learn>()
                learnList.addAll(it)
                learnList
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnDispose{
                Log.d("RemoteDataSource :", "disposed()")
            }
            .subscribe(
                { learnList ->
                    onSuccess(learnList)
                    Log.d("RemoteDataSource : ", "합치기 성공쓰")
                },
                { t ->
                    onFailure(t)
                    Log.d("RemoteDataSource : ", t.toString())
                }
            )
    }

}