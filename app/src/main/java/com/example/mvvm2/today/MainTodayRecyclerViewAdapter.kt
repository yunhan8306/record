package com.example.mvvm2.today

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mvvm2.MainActivity.Companion.TAG
import com.example.mvvm2.R
import com.example.mvvm2.SetOnClickListenerInterface
import com.example.mvvm2.databinding.TodayListItemBinding
import com.example.mvvm2.detail.DetailActivity
import com.example.mvvm2.entity.RecordEntity
import com.example.mvvm2.viewmodel.TodayViewModel

class MainTodayRecyclerViewAdapter(val context: Context, val recordList: MutableList<RecordEntity>,
                                   val todayViewModel: TodayViewModel): RecyclerView.Adapter<MainTodayRecyclerViewAdapter.MainTodayRecyclerViewHolder>() {


    // interface 객체 생성
    private var onClickListener: SetOnClickListenerInterface? = null

    // Activity에서 호출 시 객체 초기화
    fun listItemClickFunc(pOnClick: SetOnClickListenerInterface) {
        this.onClickListener = pOnClick
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainTodayRecyclerViewHolder {
        return MainTodayRecyclerViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.today_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: MainTodayRecyclerViewHolder, position: Int) {
        val record = recordList[position]
        holder.bind(record)
    }

    override fun getItemCount(): Int {
        return recordList.size
    }

    inner class MainTodayRecyclerViewHolder(val binding: TodayListItemBinding): ViewHolder(binding.root) {

        fun bind(record: RecordEntity) {
            /** content 줄바꿈 or 한 줄의 내용이 길 때 일부만 출력되게 처리 */
            var content = record.content
            if (content.indexOf("\n") != -1) {
                content = content.substring(0 until content.indexOf("\n")) + " ..."
            } else if(content.length > 12){
                content = content.substring(0, 12) + " ..."
            }
            record.content = content

            /** 데이터 바인딩 출력 */
            with(binding) {
                todayItem = record
            }

            /** detail activity 인텐트   @@ viewModel 수정? */
//            binding.item.setOnClickListener{
//                val intent = Intent(itemView.context, DetailActivity::class.java)
////            intent.putExtra("no", "${record.no}")
////            ContextCompat.startActivity(itemView.context, intent, null)
//
//
//                intent.putExtra("record", record)
//                ContextCompat.startActivity(itemView.context, intent, null)
//
//            }

            // 클릭하고자 하는 view의 리스너에 데이터 전달
            if(adapterPosition != RecyclerView.NO_POSITION){
                binding.item.setOnClickListener {
                    onClickListener?.listItemClickListener(record, binding)
                }
            }

            /** 이미지 여러개 받는거 수정 필요 */


        }
    }

}


