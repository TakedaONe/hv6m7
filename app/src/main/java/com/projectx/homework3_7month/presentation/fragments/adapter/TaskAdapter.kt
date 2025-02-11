package com.projectx.homework3_7month.presentation.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.projectx.homework3_7month.data.dto.TaskDto
import com.projectx.homework3_7month.databinding.ItemTaskBinding
import com.projectx.homework3_7month.presentation.model.TaskUI

class TaskAdapter(
    private var taskList: List<TaskUI>,
    private val onItemClick: (id:Int) -> Unit,
    private val onTaskDeleted: (TaskUI) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(taskUI: TaskUI) {
            binding.tvTask.text = taskUI.taskName
            binding.tvDate.text = taskUI.taskDate
            binding.root.setOnClickListener {
                onItemClick(taskUI.id)
            }
        }
    }

    private val itemTouch = object : ItemTouchHelper.SimpleCallback(
        0, ItemTouchHelper.LEFT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val taskToDelete = taskList[position]
            onTaskDeleted(taskToDelete)
        }
    }

    fun attachSwipeToRecyclerView(recyclerView: RecyclerView) {
        val itemTouchHelper = ItemTouchHelper(itemTouch)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task)
    }

    fun updateTasks(newTasks: List<TaskUI>) {
        taskList = newTasks
        notifyDataSetChanged()
    }
}
