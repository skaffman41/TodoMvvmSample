package com.example.todomvvmsample.data.db

import androidx.room.*
import com.example.todomvvmsample.data.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("select * from tasks where name like '%' || :searchQuery || '%' order by important desc")
    fun getTasks(searchQuery: String): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}