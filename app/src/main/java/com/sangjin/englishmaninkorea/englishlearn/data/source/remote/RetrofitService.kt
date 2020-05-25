package com.sangjin.englishmaninkorea.englishlearn.data.source.remote

import com.sangjin.englishmaninkorea.englishlearn.data.model.Quota
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers


interface RetrofitService {

    @GET("top-headlines?country=us&category=business&apiKey=3cb615fd199942709a981629b2094d8a")
    fun requestNewsList(): Observable<NewsResponse>



}
interface RetrofitServiceQuota{
    @Headers(
        "x-rapidapi-host:quotes-inspirational-quotes-motivational-quotes.p.rapidapi.com",
        "x-rapidapi-key:14c4a0b3damsh892630dc20bccf1p173207jsn7690bde1fb7e"
    )
    @GET("quote?token=ipworld.info")
    fun requestQuota() : Observable<Quota>
}



//retrofit 객체 생성
private val retrofit = Retrofit.Builder()
    .baseUrl("http://newsapi.org/v2/")
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()

private val retrofitQuota = Retrofit.Builder()
    .baseUrl("https://quotes-inspirational-quotes-motivational-quotes.p.rapidapi.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()


//retrofitService 객체 생성
object NewsApi {
    val retrofitService: RetrofitService by lazy {
        retrofit.create(RetrofitService::class.java)
    }
}

object QuotaApi{
    val retrofitService: RetrofitServiceQuota by lazy {
        retrofitQuota.create(RetrofitServiceQuota::class.java)
    }
}
