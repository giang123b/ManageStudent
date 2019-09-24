package com.example.managestudent.Repository

import androidx.lifecycle.LiveData
import com.example.managestudent.DAO.StudentDao
import com.example.managestudent.Models.Student


class StudentRepository (private val studentDao: StudentDao){
    val allStudents: LiveData<List<Student>> = studentDao.getAllWords()


    suspend fun insert(student: Student) {
        studentDao.insert(student)
    }
}