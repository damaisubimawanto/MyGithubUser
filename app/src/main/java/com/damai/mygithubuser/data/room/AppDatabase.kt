package com.damai.mygithubuser.data.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.damai.mygithubuser.data.model.room_entity.RepoDetailEntity
import com.damai.mygithubuser.data.model.room_entity.UserSearchEntity

/**
 * Created by damai.subimawanto on 2/20/2022.
 */
@Database(
    entities = [UserSearchEntity::class, RepoDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userSearchDao(): UserSearchDao

    companion object {
        fun buildDatabase(application: Application): AppDatabase {
            return Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                "appdatabase"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}