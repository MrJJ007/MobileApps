package com.example.myapplicationattempt8

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class PreferenceAdapter(var listPrefs: MutableList<PreferenceModel>, private val mDatabase: SqliteDatabase):RecyclerView.Adapter<PreferenceAdapter.PreferenceViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreferenceViewHolder {
        val inflater  = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.row_layout,parent,false)
        return PreferenceViewHolder(v)
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder, position: Int) {
        val singleProduct = listPrefs[position]
        holder.pref = singleProduct.modelName

    }

    override fun getItemCount(): Int {
        return listPrefs.size
    }
    inner class  PreferenceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        var pref = ""

        init{
            itemView.setOnClickListener(this)
        }
        override fun onClick(v:View){
            val msg = pref
            val snackbar = Snackbar.make(v,"you will receive news about $msg ", Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }

    /*
   Function to remove an item from the database, and in turn the
   recyclerview.
    */
    fun removeItem(position: Int) {
    }

    /*
    Function to add an item from the database, and in turn the
    recyclerview.
    */
    fun addItem(nameTask: String) {
        mDatabase.addTask(nameTask)
        listPrefs.clear()
        listPrefs.addAll(mDatabase.listTasks())
        notifyDataSetChanged()

    }

}
