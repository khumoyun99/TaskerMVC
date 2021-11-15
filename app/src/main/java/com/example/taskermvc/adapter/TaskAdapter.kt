package com.example.taskermvc.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskermvc.databinding.TaskTvItemBinding
import com.example.taskermvc.models.Task

class TaskAdapter(private val taskList:ArrayList<Task>,val listener:OnItemClickListener):
    RecyclerView.Adapter<TaskAdapter.Vh>() {

    inner class Vh(private val taskTvItemBinding: TaskTvItemBinding):RecyclerView.ViewHolder(taskTvItemBinding.root){

        fun onBind(task: Task){
            taskTvItemBinding.apply {

                circleImg.setOnClickListener {
                    listener.onItemClick(task)
                }
                itemTaskTv.text = task.description

                if(task.clock.isNotEmpty()){
                    alarmClockImg.visibility = View.VISIBLE
                    clockTv.visibility = View.VISIBLE
                    clockTv.text = task.clock

                }else{
                    alarmClockImg.visibility = View.GONE
                    clockTv.visibility = View.GONE
                }

                taskImg.setBackgroundColor(task.type.color)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(TaskTvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    interface OnItemClickListener{
        fun onItemClick(task: Task)
    }
}