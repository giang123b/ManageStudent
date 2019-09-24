package com.example.managestudent.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.managestudent.Database.StudentRoomDatabase
import com.example.managestudent.Models.Student
import com.example.managestudent.Repository.StudentRepository
import kotlinx.coroutines.launch

class StudentViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: StudentRepository

    val allStudents: LiveData<List<Student>>

    init {
        val wordsDao = StudentRoomDatabase.getDatabase(application, viewModelScope).studentDao()
        repository = StudentRepository(wordsDao)
        allStudents = repository.allStudents
    }

    fun insert(student: Student) = viewModelScope.launch {
        repository.insert(student)
    }
}