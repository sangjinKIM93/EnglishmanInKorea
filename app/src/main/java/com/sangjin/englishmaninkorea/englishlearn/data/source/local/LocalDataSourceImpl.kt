package com.sangjin.englishmaninkorea.englishlearn.data.source.local

import android.annotation.SuppressLint
import android.util.Log
import com.sangjin.englishmaninkorea.database.DB
import com.sangjin.englishmaninkorea.englishlearn.data.model.Learn
import com.sangjin.englishmaninkorea.englishlearn.data.model.TodayWord
import com.sangjin.englishmaninkorea.vocabularylist.data.Voca
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class LocalDataSourceImpl : LocalDataSource {

    override fun saveLearnData(learnList: List<Learn>, roomDB: DB) {
        Log.d("LocalDataSource : ", "saveLearnData() 하기 전")
        Completable.fromRunnable {
            Runnable {
                Log.d("LocalDataSource : ", "insertAll() 하기 전")
                roomDB.learnDao.deleteAllList() //이게 순서가 보장되니?
                roomDB.learnDao.insetAll(learnList)
                Log.d("LocalDataSource : ", "learn이 성공적으로 저장되었습니다.")

            }.run()
        }.subscribeOn(Schedulers.io())
            .subscribe()

    }

    override fun getLearnData(roomDB: DB, onSuccess: (learnList: List<Learn>) -> Unit, onFailure: ((t: Throwable) -> Unit)) {
        roomDB.learnDao.getLearnList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                learnList ->
                onSuccess(learnList)
            },
                {
                    onFailure(it)
                })
    }

    override fun getTodayWord(roomDB: DB, onSuccess: (learn: Learn) -> Unit) {
        roomDB.vocaDao.getRandomVoca()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { todayWords ->
                Log.d("LocalDataSource : ", "todayWord Data : $todayWords")
                val learn = Learn(2)
                learn.todayWordList = todayWords
                onSuccess(learn)
            }

    }
}