package com.sangjin.englishmaninkorea.englishalarm.alarmcreate.vocaselect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sangjin.englishmaninkorea.R
import com.sangjin.englishmaninkorea.englishalarm.alarmcreate.AlarmCreateViewModel
import com.sangjin.englishmaninkorea.vocabularylist.VocaListViewModel
import com.sangjin.englishmaninkorea.vocabularylist.adapter.VocaListAdapter
import com.sangjin.englishmaninkorea.vocabularylist.data.Voca
import kotlinx.android.synthetic.main.activity_voca_select.*

class VocaSelectActivity : AppCompatActivity() {

    private val vocaSelectAdapter by lazy{
        VocaSelectAdapter()
    }

    private val vocaListViewModel by lazy {
        ViewModelProviders.of(this).get(VocaListViewModel::class.java)
    }

    private val alarmCreateViewModel by lazy {
        ViewModelProviders.of(this).get(AlarmCreateViewModel::class.java)
    }

    private var selectList = mutableListOf<Int>()
    private var vocas = mutableListOf<Voca>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voca_select)

        recycler_vocaselect.adapter = vocaSelectAdapter
        vocaSelectAdapter.onItemSelectedListener = {selectPostions ->
            selectList.clear()
            selectList.addAll(selectPostions)
        }

        vocaListViewModel.vocaList.observe(this, Observer {vocaList ->
            vocaSelectAdapter.submitList(vocaList)
            vocas.addAll(vocaList)
        })


        btn_vocaselect.setOnClickListener {
            for(position in selectList){
                alarmCreateViewModel.addWordList(vocas.get(position).englishWord, vocas.get(position).koreanMeaning)
            }

            finish()
        }

    }
}
