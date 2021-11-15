package com.example.taskermvc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskermvc.databinding.ListsItemRvBinding
import com.example.taskermvc.models.Task
import com.example.taskermvc.models.Type

class TypeAdapter(
    private val typeList: ArrayList<Type>,
    val taskCount: Int,
    val listener: OnItemClickListener
):RecyclerView.Adapter<TypeAdapter.Vh>() {

    inner class Vh(private val listItemRvBinding: ListsItemRvBinding):RecyclerView.ViewHolder(listItemRvBinding.root){

        fun onBind(type:Type){
            listItemRvBinding.apply {
                personalTv.text = type.title
                tasksCountPersonalTv.text = "$taskCount tasks"
                personalLl.setBackgroundColor(type.color)
            }
            itemView.setOnClickListener {
                listener.onItemClick(type)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ListsItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(typeList[position])
    }

    override fun getItemCount(): Int {
        return typeList.size
    }

    interface OnItemClickListener{
        fun onItemClick(type: Type)
    }
}