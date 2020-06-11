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
import com.sangjin.englishmaninkorea.databinding.ActivityAlarmListBinding
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

    private lateinit var binding : ActivityAlarmListBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentView = inflater.inflate(R.layout.activity_alarm_list, container, false)
        binding = ActivityAlarmListBinding.bind(fragmentView)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        recyclerViewSetting()   //리사이클러뷰 셋팅
        btnClickListenerList()  //버튼 클릭 리스너 모음

        viewModel.alarmList.observe(viewLifecycleOwner, Observer {
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
            intent.putExtra("TIME", viewModel.alarmList.value?.alarmTime)
            startActivity(intent)
        }
    }


    private fun checkAlarmExist(it: Alarm?) {

        if (it == null) {   //등록된 알람이 없는 경우
            viewModel.isCreated.value = true

        } else {
            viewModel.isCreated.value = false
            viewModel.isEdited.value = true

            //등록된 알람이 있는 경우 데이터 셋팅
            fragmentView.alarmTimeShowTV.text = it.alarmTime
            alarmCreateAdapter.submitList(it.wordList)
        }

        //constraintGroup의 경우 requestLayout 해주지 않으면 변경된 속성이 저장되지 않는다
        refreshGroupView()
    }


    private fun refreshGroupView() {
        fragmentView.groupCreateAlarm.requestLayout()
        fragmentView.groupEditAlarm.requestLayout()
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
