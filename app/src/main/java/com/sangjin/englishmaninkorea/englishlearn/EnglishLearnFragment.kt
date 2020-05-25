package com.sangjin.englishmaninkorea.englishlearn

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.sangjin.englishmaninkorea.R
import com.sangjin.englishmaninkorea.database.DB
import com.sangjin.englishmaninkorea.englishlearn.adapter.LearnListAdapter
import com.sangjin.englishmaninkorea.englishlearn.data.repository.LearnDataRepositoryImpl
import com.sangjin.englishmaninkorea.englishlearn.data.source.local.LocalDataSourceImpl
import com.sangjin.englishmaninkorea.englishlearn.data.source.remote.RemoteDataSourceImpl
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_english_learn.view.*


class EnglishLearnFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_english_learn, container, false)

        val learnListAdapter = LearnListAdapter()
        view.recycler_learn.adapter = learnListAdapter

        val learnDataRepositoryImpl = LearnDataRepositoryImpl(RemoteDataSourceImpl(), LocalDataSourceImpl())

        learnDataRepositoryImpl.getEnglishContent(
            DB.getInstance(activity!!.applicationContext),
            onSuccess = { learnList ->
                learnListAdapter.updateList(learnList)
                view.progressbar_loading.hide()
            },
            onFailure = { t ->

                view.progressbar_loading.hide()
            }
        )

        return view
    }

}
