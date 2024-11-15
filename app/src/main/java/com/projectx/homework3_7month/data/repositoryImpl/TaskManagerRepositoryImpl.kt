package com.projectx.homework3_7month.data.repositoryImpl

import com.projectx.homework3_7month.data.database.dao.TaskManagerDao
import com.projectx.homework3_7month.data.dto.toData
import com.projectx.homework3_7month.data.dto.toDomain
import com.projectx.homework3_7month.domain.model.TaskModel
import com.projectx.homework3_7month.domain.repository.TaskManagerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskManagerRepositoryImpl(private val taskManagerDao: TaskManagerDao) :
    TaskManagerRepository {

    override suspend fun insertTask(taskModel: TaskModel) {
        taskManagerDao.insertTask(taskModel.toData())
    }

    override suspend fun getAllNotes():  Flow<List<TaskModel>> {
        return taskManagerDao.getAllNotes().map {list ->
            list.map {dto->
                dto.toDomain()
            }
        }
    }

    override suspend fun getTaskByName(taskName: String): TaskModel? {
        return taskManagerDao.getTaskByName(taskName)?.toDomain()
    }

    override suspend fun updateTask(taskModel: TaskModel) {
        taskManagerDao.updateTask(taskModel.toData())

    }

    override suspend fun deleteTask(task: TaskModel) {
        taskManagerDao.deleteTask(task.toData())
    }

    override suspend fun getTask(id: Int): TaskModel? {
        val taskDto = taskManagerDao.getTaskById(id)
       return taskDto?.toDomain()
    }


}