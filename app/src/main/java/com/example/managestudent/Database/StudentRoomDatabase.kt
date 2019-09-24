package com.example.managestudent.Database;

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.managestudent.DAO.StudentDao
import com.example.managestudent.Models.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Student::class], version = 2)
abstract class StudentRoomDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var INSTANCE: StudentRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): StudentRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentRoomDatabase::class.java,
                    "student_database"
                )
                    .addCallback(
                        WordDatabaseCallback(
                            scope
                        )
                    )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(
                            database.studentDao()
                        )
                    }
                }
            }
        }

        suspend fun populateDatabase(studentDao: StudentDao) {
            studentDao.deleteAll()

            var student =
                Student("123", "Bùi Hoàng Đạt", "09123212381")
            studentDao.insert(student)
            student = Student("332", "Lê Hải Dương", "09123212382")
            studentDao.insert(student)
            student =
                Student("111", "Nguyễn Minh Giang", "09726123223")
            studentDao.insert(student)
            student =
                Student("9821", "Hồ Quốc Cường", "09312381232")
            studentDao.insert(student)
            student =
                Student("09123", "Nguyễn Bá Hùng", "09312381233")
            studentDao.insert(student)
            student =
                Student("712", "Nguyễn Việt Đoàn", "09123212384")
            studentDao.insert(student)
            student =
                Student("376332", "Lê Hải Dương", "09312381235")
            studentDao.insert(student)
            student =
                Student("621", "Do Truong Giang", "0356865077")
            studentDao.insert(student)

        }
    }
}