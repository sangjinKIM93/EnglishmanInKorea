package com.sangjin.englishmaninkorea.vocabularylist.naverword

import android.content.Context
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.sangjin.englishmaninkorea.R
import com.sangjin.englishmaninkorea.vocabularylist.VocaListViewModel
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.activity_web_view.view.*

class WebViewActivity : AppCompatActivity() {

    private val viewModel by lazy{
        ViewModelProviders.of(this).get(VocaListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val targetUrl = pref.getString("URL", "")


        webView.setWebViewClient(object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressbar.visibility = View.INVISIBLE
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressbar.visibility = View.VISIBLE
            }
        }) // 클릭시 새창 안뜨게

        val webViewSetting = webView.getSettings() //세부 세팅 등록
        webViewSetting.setJavaScriptEnabled(true) // 웹페이지 자바스클비트 허용 여부
        webViewSetting.setSupportMultipleWindows(false) // 새창 띄우기 허용 여부
        webViewSetting.setJavaScriptCanOpenWindowsAutomatically(false) // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        webViewSetting.setLoadWithOverviewMode(true) // 메타태그 허용 여부
        webViewSetting.setUseWideViewPort(true) // 화면 사이즈 맞추기 허용 여부
        webViewSetting.setSupportZoom(false) // 화면 줌 허용 여부
        webViewSetting.setBuiltInZoomControls(false) // 화면 확대 축소 허용 여부
        webViewSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN) // 컨텐츠 사이즈 맞추기
        webViewSetting.setCacheMode(WebSettings.LOAD_NO_CACHE) // 브라우저 캐시 허용 여부
        webViewSetting.setDomStorageEnabled(true) // 로컬저장소 허용 여부


        webView.loadUrl(targetUrl)


        et_koreanMeaning_webview.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){

                naverVocaAddBtn(v)

                true
            }
            else{
                false
            }
        }


    }


    fun naverVocaAddBtn(view : View){

        if(TextUtils.isEmpty(et_englishWord_webview.text) || TextUtils.isEmpty(et_koreanMeaning_webview.text)){
            Toast.makeText(this, R.string.toast_no_edt, Toast.LENGTH_LONG).show()
        }
        else{
            viewModel.addList(et_englishWord_webview.text.toString(), et_koreanMeaning_webview.text.toString())
            et_englishWord_webview.setText("")
            et_englishWord_webview.requestFocus()
            et_koreanMeaning_webview.setText("")

            Toast.makeText(this, R.string.toast_add_word, Toast.LENGTH_SHORT).show()
        }

    }
}
