package com.example.todomvvmsample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todomvvmsample.data.Task
import com.example.todomvvmsample.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Помыть посуду", important = true))
                dao.insert(Task("Сходить в магазин"))
                dao.insert(Task("Побороть соседа", important = true))
                dao.insert(Task("Выкинуть компьютер"))
                dao.insert(Task("Клевать носом", completed = true))
                dao.insert(Task("Развернуть пленку", important = true))
            }
        }
    }
}