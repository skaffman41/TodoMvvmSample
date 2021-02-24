package com.example.todomvvmsample.di

import android.app.Application
import androidx.room.Room
import com.example.todomvvmsample.data.db.TaskDao
import com.example.todomvvmsample.data.db.TaskDatabase
import com.example.todomvvmsample.data.repo.TasksRepository
import com.example.todomvvmsample.data.repo.TasksRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [AppModule.AppModuleBinds::class])
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(
        application: Application,
        callback: TaskDatabase.Callback
    ): TaskDatabase {
        return Room.databaseBuilder(application, TaskDatabase::class.java, "task_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }

    @Provides
    fun provideTaskDao(db: TaskDatabase): TaskDao {
        return db.taskDao()
    }

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface AppModuleBinds {
        @Binds
        @Singleton
        fun bindsRepository(tasksRepository: TasksRepositoryImpl): TasksRepository
    }
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope