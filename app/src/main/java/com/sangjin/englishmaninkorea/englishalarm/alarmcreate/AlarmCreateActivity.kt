package com.sangjin.englishmaninkorea.englishalarm.alarmcreate

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.sangjin.englishmaninkorea.R
import com.sangjin.englishmaninkorea.englishalarm.alarmcreate.adapter.AlarmCreateAdapter
import com.sangjin.englishmaninkorea.englishalarm.alarmcreate.vocaselect.VocaSelectActivity
import com.sangjin.englishmaninkorea.englishalarm.data.Word
import kotlinx.android.synthetic.main.activity_alarm_create.*
import kotlinx.android.synthetic.main.activity_voca_select.*

class AlarmCreateActivity : AppCompatActivity() {

    private val viewModel: AlarmCreateViewModel by lazy {
        ViewModelProviders.of(this).get(AlarmCreateViewModel::class.java)
    }

    private val alarmCreateAdapter: AlarmCreateAdapter by lazy {
        val onItemClickListener : ((Int)->Unit) = {position ->
            removeWord(position)
        }

        AlarmCreateAdapter(onItemClickListener)
    }

    private val timepicker: TimePicker by lazy{
        findViewById<TimePicker>(R.id.timePicker)
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_create)

        timepicker.setIs24HourView(true)

        checkEdit() //수정으로 부른건지 추가로 만든건지 확인
        initTimePicker()
        initRecyclerView()


        //알람 추가 버튼 클릭시 이벤트
        alarmAddFinishBtn.setOnClickListener {
            if(viewModel.wordList.value?.size == 0){
                Toast.makeText(this, "단어를 등록해주세요", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addAlarm()
                finish()
            }
        }

        //키패드 설정
        wordMeaningET.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){

                wordAddClick(v)

                true
            }
            else{
                false
            }
        }
        showKeyPad()

        viewModel.wordList.observe(this, Observer {wordList ->
            alarmCreateAdapter.submitList(wordList)
            alarmCreateAdapter.notifyDataSetChanged()
            emptyCheck(wordList)
        })


        toVocaListBtn.setOnClickListener {
            val intent = Intent(this, VocaSelectActivity::class.java)
            startActivity(intent)
        }

    }


    //view에서 설정한 clickListener는 private으로 하면 안 됨.
    fun wordAddClick(view: View) {

        if(TextUtils.isEmpty(englishWordET.text) || TextUtils.isEmpty(wordMeaningET.text)){
            Toast.makeText(this, R.string.toast_no_edt, Toast.LENGTH_LONG).show()
        }
        else{
            viewModel.addWordList(englishWordET.text.toString(), wordMeaningET.text.toString())
            englishWordET.setText("")
            englishWordET.requestFocus()
            wordMeaningET.setText("")
        }
    }


    private fun showKeyPad(){
        englishWordET.requestFocus()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }


    private fun checkEdit() {
        //알람 시간 받아서 timepicker 초기화 해주기
        val intent = intent
        val item = intent.getStringExtra("TIME")

        if (item != null) {

            //timepicker시간을 전 예약시간으로 설정
            timePicker.setTimeExisting(item)

            //버튼 바꾸기
            alarmAddFinishBtn.text = "수  정"
        }
    }


    private fun initTimePicker() {

        //초기화
        if (Build.VERSION.SDK_INT < 23) {
            viewModel.hour = timepicker.currentHour
            viewModel.min = timepicker.currentMinute
        } else {
            viewModel.hour = timepicker.hour
            viewModel.min = timepicker.minute
        }

        //시간 변화 리스너
        timepicker.setOnTimeChangedListener { it, hour, min ->
            viewModel.hour = hour
            viewModel.min = min
        }
    }


    private fun removeWord(position: Int) {

        val word = viewModel.wordList.value?.get(position) as Word

        val builder = AlertDialog.Builder(this)
        builder.setTitle("삭제")
        builder.setMessage("${word}를 삭제하시겠습니까?")
        builder.setPositiveButton("네") { dialog, which ->
            viewModel.removeWord(word)
        }
        builder.setNegativeButton("아니요") { dialog, which -> }
        builder.show()

    }


    private fun initRecyclerView() {

        val layoutManager = FlexboxLayoutManager(this)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.CENTER
        layoutManager.flexWrap = FlexWrap.WRAP

        wordRecyclerView.layoutManager = layoutManager

        wordRecyclerView.adapter = alarmCreateAdapter
    }


    //단어를 입력하지 않으면 안내 문구 출력
    private fun emptyCheck(it: List<Word>) {
        if (it.size > 0) {
            wordRecyclerView.visibility = View.VISIBLE
            guideMessageToInput.visibility = View.INVISIBLE
        } else {
            wordRecyclerView.visibility = View.INVISIBLE
            guideMessageToInput.visibility = View.VISIBLE
        }
    }

}
