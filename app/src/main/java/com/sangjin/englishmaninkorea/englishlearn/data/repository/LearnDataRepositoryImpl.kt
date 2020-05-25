package com.sangjin.englishmaninkorea.englishlearn.data.repository

import android.util.Log
import com.sangjin.englishmaninkorea.database.DB
import com.sangjin.englishmaninkorea.englishlearn.data.model.Learn
import com.sangjin.englishmaninkorea.englishlearn.data.source.local.LocalDataSourceImpl
import com.sangjin.englishmaninkorea.englishlearn.data.source.remote.RemoteDataSourceImpl

class LearnDataRepositoryImpl(
    private val remoteDataSourceImpl: RemoteDataSourceImpl,
    private val localDataSourceImpl: LocalDataSourceImpl
) : LearnDataRepository {

    override fun getEnglishContent(
        roomDB: DB,
        onSuccess: (learnList: List<Learn>) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {

        //로컬 캐시 자료 가져오기
        localDataSourceImpl.getLearnData(roomDB, onSuccess, onFailure)

        //remote 통신 후 localcache에 저장 후 최신화
        remoteDataSourceImpl.getEnglishContent(
            onSuccess = { learnList ->

                //learnList에 local에서 받아온 todayWord 합치기
                localDataSourceImpl.getTodayWord(roomDB,
                onSuccess = {todayWords ->

                    learnList.add(1, todayWords)    //근데 여기서 만약에 null이여서 못 가져오면?

                    //local cache 저장
                    localDataSourceImpl.saveLearnData(learnList, roomDB)

                    //그리고 그 데이터로 최신화
                    onSuccess(learnList)
                })

            },
            onFailure = { t ->
                onFailure(t)
            }
        )
    }
}