package com.d121201008.task_management.list

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.convertTo
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView
import com.d121201008.task_management.R
import com.d121201008.task_management.database.Task
import kotlinx.android.synthetic.main.fragment_list_item.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    private var tasks = emptyList<Task>()

    class ListViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
       return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = tasks[position]
        holder.itemView.text_title.text = currentItem.title
        holder.itemView.text_detail.text = currentItem.detail
        holder.itemView.text_category.text = currentItem.category
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun setData(tasks: List<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }
}
