package com.sangjin.englishmaninkorea.vocabularylist

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView

import com.sangjin.englishmaninkorea.R
import com.sangjin.englishmaninkorea.vocabularylist.adapter.VocaListAdapter
import com.sangjin.englishmaninkorea.vocabularylist.naverword.WebViewActivity
import com.sangjin.englishmaninkorea.vocabularylist.swipe.MyButton
import com.sangjin.englishmaninkorea.vocabularylist.swipe.MyButtonClickListener
import com.sangjin.englishmaninkorea.vocabularylist.swipe.MySwipeHelper
import kotlinx.android.synthetic.main.fragment_word_list.view.*


class VocaListFragment : Fragment() {

    private val vocaListAdapter by lazy {
        val onItemClickListener : ((Int)->Unit) = {position ->

        }

        VocaListAdapter(onItemClickListener)
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(VocaListViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_word_list, container, false)


        view.recycler_vocaList.adapter = vocaListAdapter

//        val swipeController = SwipeController()
//        val itemTouchHelper = ItemTouchHelper(swipeController)
//        itemTouchHelper.attachToRecyclerView(view.recycler_vocaList)
//        view.recycler_vocaList.addItemDecoration(object : RecyclerView.ItemDecoration(){
//            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//                swipeController.onDraw(c)
//            }
//        })

        val swipe = object: MySwipeHelper(requireContext(), view.recycler_vocaList, 200){
            override fun instantiateMyButton(
                viewHolder: RecyclerView.ViewHolder,
                buffer: MutableList<MyButton>
            ) {
                buffer.add(MyButton(requireContext(),
                "Delete",
                    30,
                    0,
                    Color.parseColor("#FF3c30"),
                    object: MyButtonClickListener{
                        override fun onClick(pos: Int) {
                            Toast.makeText(requireContext(), "Delete ID"+pos, Toast.LENGTH_SHORT).show()
                        }
                    }
                ))
                buffer.add(MyButton(requireContext(),
                    "Update",
                    30,
                    0,
                    Color.parseColor("#FF3c30"),
                    object: MyButtonClickListener{
                        override fun onClick(pos: Int) {
                            Toast.makeText(requireContext(), "Update ID"+pos, Toast.LENGTH_SHORT).show()
                        }
                    }
                ))
            }

        }


        viewModel.vocaList.observe(viewLifecycleOwner, Observer {
            vocaListAdapter.submitList(it)
            vocaListAdapter.notifyDataSetChanged()
        })


        view.et_koreanMeaning.setOnEditorActionListener { v, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE){

                vocaAddClick(v)
                true
            }
            else{
                false
            }
        }


        view.btn_toWebView.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        view.btn_vocaAdd.setOnClickListener{
            vocaAddClick(view)
        }

        return view
    }


    //view에서 설정한 clickListener는 private으로 하면 안 됨.
    fun vocaAddClick(view: View) {

        if(TextUtils.isEmpty(view.et_englishWord.text) || TextUtils.isEmpty(view.et_koreanMeaning.text)){
            Toast.makeText(requireContext(), R.string.toast_no_edt, Toast.LENGTH_LONG).show()
        }
        else{
            viewModel.addList(view.et_englishWord.text.toString(), view.et_koreanMeaning.text.toString())
            view.et_englishWord.setText("")
            view.et_englishWord.requestFocus()
            view.et_koreanMeaning.setText("")

            Toast.makeText(requireContext(), R.string.toast_add_word, Toast.LENGTH_SHORT).show()
        }
    }

}
