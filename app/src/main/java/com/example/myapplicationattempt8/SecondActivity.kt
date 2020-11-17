package com.example.myapplicationattempt8

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View

import com.google.gson.JsonArray;
import com.koushikdutta.ion.Ion;

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class SecondActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val mToolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(mToolbar)


        val imageModelArrayList = populateList()
        val recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val mAdapter = MyAdapter(imageModelArrayList)
        recyclerView.adapter = mAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate((R.menu.toolbar_layout), menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val myView = findViewById<View>(R.id.main_toolbar)
        when(item!!.itemId) {
            R.id.refresh -> {
                Snackbar.make(myView, getString(R.string.refresh),Snackbar.LENGTH_SHORT).show()
                return true
            }
            R.id.loginOrSignup -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.preferences -> {
                val intent = Intent(this, PreferenceActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun populateList(): ArrayList<MyModel>{
        val list = ArrayList<MyModel>()
        val myImageList = arrayOf(R.drawable.car,R.drawable.clouds,R.drawable.square)
        val myImageNameList = arrayOf(R.string.car,R.string.clouds,R.string.square)
        val myImageTextList = arrayOf(R.string.text1, R.string.text2, R.string.text3)

        Ion.with(this).load("GET","https://catfact.ninja/fact?max_length=140")
                .asString()
                .setCallback{ex, result ->
                    //var myImageNameListadded = append(myImageNameList, ex)
                    Log.d("return",ex.toString())
                }

        for (i in 0..2){
            val imageModel = MyModel()
            imageModel.setNames(getString(myImageNameList[i]))
            imageModel.setImages(myImageList[i])
            imageModel.setText(getString(myImageTextList[i]))
            list.add(imageModel)
        }

        return list
    }
    //fun append(arr:Array<Int>, element: Int):Array<Int>{

    //}

}
