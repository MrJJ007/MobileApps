package com.example.myapplicationattempt8

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.floatingactionbutton.FloatingActionButton

class PreferenceActivity : AppCompatActivity() {
    val list = ArrayList<PreferenceModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)

        val mDatabase = SqliteDatabase(this)
        val mutableTasks: MutableList<PreferenceModel> = mDatabase.listTasks()
        //val imageModelArrayList = populateList()
        val recyclerView = findViewById<View>(R.id.recycler_view_pref) as RecyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val mAdapter = PreferenceAdapter(mutableTasks,mDatabase)
        recyclerView.adapter = mAdapter

        val fab = findViewById<Button>(R.id.add)
        fab.setOnClickListener(){
            addPrefDialog(mDatabase, mAdapter)
        }
    }
    private fun addPrefDialog(mDatabase: SqliteDatabase, mAdapter: PreferenceAdapter){
        val inflater = LayoutInflater.from(this)
        val addTaskView = inflater.inflate(R.layout.activity_preference, null)
        val taskNameField = addTaskView.findViewById(R.id.preferenceField) as EditText

        val builder = AlertDialog.Builder(this)
            .setTitle(getString(R.string.prefTitle))
            .setView(addTaskView)
        builder.create()
        builder.setPositiveButton(getString(R.string.addPref)){_, _->
            val name = taskNameField.text.toString()

            if (!TextUtils.isEmpty(name)){
                mAdapter.addItem(name)
            }
            Log.d("stored_values",mAdapter.getItemCount().toString())
        }
        builder.setNegativeButton(getString(R.string.cancel)) { _, _ ->
        }
        builder.show()
    }
}


