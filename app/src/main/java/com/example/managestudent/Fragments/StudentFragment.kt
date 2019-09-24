package com.example.managestudent.Fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import com.example.managestudent.Activities.NewStudentActivity
import com.example.managestudent.Models.Student
import com.example.managestudent.R
import com.example.managestudent.Adapters.StudentListAdapter
import com.example.managestudent.ViewModel.StudentViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentFragment : Fragment() {
    private lateinit var studentViewModel: StudentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_student, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter =
            StudentListAdapter(activity!!.applicationContext)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)
        studentViewModel.allStudents.observe(this, Observer { student ->
            // Update the cached copy of the words in the adapter.
            student?.let { adapter.setStudents(it) }
        })

        val fab = rootView.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(activity, NewStudentActivity::class.java)
            startActivityForResult(intent,
                newStudentActivityRequestCode
            )
        }

        val vertical = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(vertical)

        return rootView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newStudentActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let {

                val student = Student(
                    it.getStringExtra(NewStudentActivity.EXTRA_REPLY_ID),
                    it.getStringExtra(NewStudentActivity.EXTRA_REPLY_NAME),
                    it.getStringExtra(NewStudentActivity.EXTRA_REPLY_PHONE)
                )

                studentViewModel.insert(student)
            }
        } else {
            Toast.makeText(
                context,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        const val newStudentActivityRequestCode = 1
    }

}
