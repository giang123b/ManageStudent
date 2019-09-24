package com.example.managestudent.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "student_table")
class Student(@PrimaryKey @ColumnInfo(name = "id") val id: String, @ColumnInfo(name = "name") val name: String,
              @ColumnInfo(name = "phone") val phone: String)