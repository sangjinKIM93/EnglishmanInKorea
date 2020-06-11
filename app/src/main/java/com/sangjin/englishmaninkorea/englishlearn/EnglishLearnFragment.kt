package com.sangjin.englishmaninkorea.englishlearn

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.sangjin.englishmaninkorea.R
import com.sangjin.englishmaninkorea.database.DB
import com.sangjin.englishmaninkorea.databinding.FragmentEnglishLearnBinding
import com.sangjin.englishmaninkorea.englishalarm.alarmcreate.AlarmCreateViewModel
import com.sangjin.englishmaninkorea.englishlearn.adapter.LearnListAdapter
import com.sangjin.englishmaninkorea.englishlearn.data.repository.LearnDataRepositoryImpl
import com.sangjin.englishmaninkorea.englishlearn.data.source.local.LocalDataSourceImpl
import com.sangjin.englishmaninkorea.englishlearn.data.source.remote.RemoteDataSourceImpl
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_english_learn.view.*


class EnglishLearnFragment : Fragment() {

    private val learnListAdapter by lazy {
        LearnListAdapter()
    }

    private val englishLearnViewModel : EnglishLearnViewModel by lazy {
        ViewModelProviders.of(this).get(EnglishLearnViewModel::class.java)
    }

    private lateinit var binding : FragmentEnglishLearnBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_english_learn, container, false)
        binding.viewModel = englishLearnViewModel
        binding.lifecycleOwner = this

        setRecyclerView()


        return binding.root
    }


    private fun setRecyclerView() {
        binding.recyclerLearn.adapter = learnListAdapter
    }

}
