package com.sangjin.englishmaninkorea.vocabularylist.naverword

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sangjin.englishmaninkorea.R
import kotlinx.android.synthetic.main.activity_naver_word_link.*

class NaverWordLinkActivity : AppCompatActivity() {

    private val TAG = "NaverWordLinkActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_naver_word_link)

        btn_save_naverurl.setOnClickListener {
            //shared에 주소 저장
            val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
            val ed = pref.edit()
            ed.putString("URL", et_naverurl.text.toString()).apply()

            Log.d(TAG, "inputURL: ${et_naverurl.text}")
        }

        btn_toNaverLink.setOnClickListener {
            val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
            val targetUrl = pref.getString("URL", "")

            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("URL", targetUrl)
            startActivity(intent)

            Log.d(TAG, "outputURL: ${targetUrl}")
        }

    }
}
