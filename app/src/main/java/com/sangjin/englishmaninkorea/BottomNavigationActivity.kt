package com.sangjin.englishmaninkorea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sangjin.englishmaninkorea.englishalarm.alarmlist.AlarmListFragment
import com.sangjin.englishmaninkorea.englishlearn.EnglishLearnFragment
import com.sangjin.englishmaninkorea.vocabularylist.VocaListFragment
import kotlinx.android.synthetic.main.activity_bottom_navigation.*
import nl.joery.animatedbottombar.AnimatedBottomBar

class BottomNavigationActivity : AppCompatActivity() {

    private val viewModel: BottomNavigationViewModel by lazy {
        ViewModelProviders.of(this).get(BottomNavigationViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_bottom_navigation)

        viewModel.fragmentStatus.observe(this, Observer {
            when(it){
                0 -> {
                    val todoFragment = AlarmListFragment()
                    loadFragment(todoFragment)
                    bottom_navigation.selectTabAt(0)
                }
                1 -> {
                    val vocaFragment = VocaListFragment()
                    loadFragment(vocaFragment)
                    bottom_navigation.selectTabAt(1)
                }
                2 -> {
                    val englishLearnFragment = EnglishLearnFragment()
                    loadFragment(englishLearnFragment)
                    bottom_navigation.selectTabAt(2)
                }
            }
        })

        //네비게이션 클릭 리스너(클릭시 해당 프래그먼트로 이동)
        setNavigationItemClickListener()

    }


    private fun setNavigationItemClickListener(){
        bottom_navigation.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener{
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                when (newTab.id) {
                    R.id.navigation_alarm -> {
                        viewModel.updateFragmentStatus(0)       //여기서는 fragment 상태만 업데이트 하고 최신화는 observer에서 하도록 한다.
                    }
                    R.id.navigation_vocabulary -> {
                        viewModel.updateFragmentStatus(1)
                    }
                    R.id.navigation_learn -> {
                        viewModel.updateFragmentStatus(2)
                    }
                }
            }

        })
    }


    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
//        transaction.addToBackStack(null)
        transaction.commit()
    }
}
