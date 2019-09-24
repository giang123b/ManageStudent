package com.example.managestudent.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import com.example.managestudent.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.mnuHelp -> showHelp()
            R.id.mnuAbout -> showMenu()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showMenu() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    private fun showHelp() {
        val intent = Intent(this, HelpActivity::class.java)
        startActivity(intent)
    }

}