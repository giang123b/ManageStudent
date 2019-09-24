package com.example.managestudent.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.Models.Student
import com.example.managestudent.R

class StudentListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var students = emptyList<Student>()

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val id: TextView = itemView.findViewById(R.id.txtID)
        val name: TextView = itemView.findViewById(R.id.txtName)
        val phone: TextView = itemView.findViewById(R.id.txtPhone)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = inflater.inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val current = students[position]
        holder.id.text = current.id
        holder.name.text = current.name
        holder.phone.text = current.phone
    }

    internal fun setStudents(students: List<Student>) {
        this.students = students
        notifyDataSetChanged()
    }

    override fun getItemCount() = students.size
}