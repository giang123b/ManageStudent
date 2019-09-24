package com.example.managestudent.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.managestudent.R

class NewStudentActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtPhone: EditText
    private lateinit var edtID: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)
        setContentView(R.layout.activity_new_student)

        addControls()

        val button = findViewById<Button>(R.id.btnAddAStudent)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(edtID.text) || TextUtils.isEmpty(edtName.text) || TextUtils.isEmpty(edtPhone.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val id = edtID.text.toString()
                val name = edtName.text.toString()
                val phone = edtPhone.text.toString()

                replyIntent.putExtra(EXTRA_REPLY_ID, id)
                replyIntent.putExtra(EXTRA_REPLY_NAME, name)
                replyIntent.putExtra(EXTRA_REPLY_PHONE, phone)

                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    private fun addControls() {
        edtID = findViewById(R.id.edtID)
        edtName = findViewById(R.id.edtName)
        edtPhone = findViewById(R.id.edtPhone)
    }

    companion object {
        const val EXTRA_REPLY_ID = "com.example.studentlistsql.REPLY_ID"
        const val EXTRA_REPLY_NAME = "com.example.studentlistsql.REPLY_NAME"
        const val EXTRA_REPLY_PHONE = "com.example.studentlistsql.REPLY_PHONE"
    }
}

