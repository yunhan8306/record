package com.example.mvvm2.total

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm2.R
import com.example.mvvm2.databinding.ActivityDetailBinding
import com.example.mvvm2.detail.DetailUpdateActivity
import com.example.mvvm2.entity.RecordEntity
import com.example.mvvm2.viewmodel.DetailViewModel

class MainTotalRecyclerViewAdapter(
    val context: Context, val recordList: MutableList<RecordEntity>,
    val detailViewModel: DetailViewModel
): RecyclerView.Adapter<MainTotalRecyclerViewAdapter.MainTotalRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainTotalRecyclerViewHolder {
        return MainTotalRecyclerViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.activity_detail, parent, false))
    }

    override fun onBindViewHolder(holder: MainTotalRecyclerViewHolder, position: Int) {
        val record = recordList[position]
        holder.bind(record)
    }

    override fun getItemCount(): Int {
        return recordList.size
    }

    inner class MainTotalRecyclerViewHolder(val binding: ActivityDetailBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(record: RecordEntity) {
            /** 데이터 바인딩 출력 */
            with(binding) {
                viewDetail = record
            }

            /** 수정, 삭제 */
            binding.btnUpdate.setOnClickListener {

                val intent = Intent(context, DetailUpdateActivity::class.java)
                intent.putExtra("record", record)
                context.startActivity(intent)
            }
            binding.btnRemove.setOnClickListener {
                detailViewModel.deleteData(record)
            }

            /** 이미지 뷰페이저 필요 */
        }
    }
}

