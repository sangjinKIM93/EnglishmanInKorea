package com.sangjin.englishmaninkorea.englishalarm.alarmlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.sangjin.englishmaninkorea.R
import com.sangjin.englishmaninkorea.englishalarm.alarmcreate.AlarmCreateActivity
import com.sangjin.englishmaninkorea.englishalarm.alarmlist.adapter.AlarmListAdapter
import com.sangjin.englishmaninkorea.englishalarm.data.Alarm
import kotlinx.android.synthetic.main.activity_alarm_list.view.*

class AlarmListFragment : Fragment() {

    val alarmCreateAdapter: AlarmListAdapter by lazy {
        AlarmListAdapter()
    }

    val viewModel by lazy {
        ViewModelProviders.of(this).get(AlarmListViewModel::class.java)
    }

    private lateinit var fragmentView: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentView = inflater.inflate(R.layout.activity_alarm_list, container, false)

        recyclerViewSetting()   //리사이클러뷰 셋팅
        btnClickListenerList()  //버튼 클릭 리스너 모음

        viewModel.alarmList?.observe(viewLifecycleOwner, Observer {
            Log.d("AlarmListActivity : ", "옵저버 동작 : ${it}")
            checkAlarmExist(it) //알람이 존재하는지 확인 후 view 결정
        })

        return fragmentView
    }

    private fun btnClickListenerList() {
        //추가 버튼 클릭시
        fragmentView.alarmAddBtn.setOnClickListener {
            startActivity(Intent(requireContext(), AlarmCreateActivity::class.java))
        }

        //삭제 버튼 클릭시
        fragmentView.alarmDeleteBtn.setOnClickListener {

            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("삭제")
            builder.setMessage("알람을 삭제하시겠습니까?")
            builder.setPositiveButton("네") { dialog, which ->
                viewModel.deleteAlarm()
            }
            builder.setNegativeButton("아니요") { dialog, which -> }
            builder.show()
        }

        fragmentView.alarmEditBtn.setOnClickListener {
            val intent = Intent(requireContext(), AlarmCreateActivity::class.java)
            intent.putExtra("TIME", viewModel.alarmList?.value?.alarmTime)
            startActivity(intent)
        }
    }


    private fun checkAlarmExist(it: Alarm?) {

        if (it == null) {   //등록된 알람이 없는 경우

            fragmentView.groupCreateAlarm.visibility = View.VISIBLE
            fragmentView.groupCreateAlarm.requestLayout()
            fragmentView.groupEditAlarm.visibility = View.INVISIBLE
            fragmentView.groupEditAlarm.requestLayout()

        } else {
            //등록된 알람이 있는 경우 데이터 셋팅
            fragmentView.alarmTimeShowTV.text = it.alarmTime
            alarmCreateAdapter.submitList(it.wordList)

            fragmentView.groupEditAlarm.visibility = View.VISIBLE
            fragmentView.groupEditAlarm.requestLayout()
            fragmentView.groupCreateAlarm.visibility = View.INVISIBLE
            fragmentView.groupCreateAlarm.requestLayout()

        }
    }


    private fun recyclerViewSetting() {
        val layoutManager = FlexboxLayoutManager(requireContext())
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.CENTER
        layoutManager.flexWrap = FlexWrap.WRAP

        fragmentView.alarmListWordRecyclerView.adapter = alarmCreateAdapter
        fragmentView.alarmListWordRecyclerView.layoutManager = layoutManager
    }
}
