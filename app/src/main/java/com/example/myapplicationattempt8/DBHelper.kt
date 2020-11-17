package com.example.myapplicationattempt8

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteDatabase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TASKS_TABLE = "CREATE TABLE $TABLE_PREFS($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_PREF_TITLE TEXT)"
        db.execSQL(CREATE_TASKS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PREFS")
        onCreate(db)
    }

    fun listTasks(): MutableList<PreferenceModel> {
        val sql = "select * from $TABLE_PREFS"
        val db = this.readableDatabase
        val storeTasks = arrayListOf<PreferenceModel>()
        val cursor = db.rawQuery(sql,null)
        if (cursor.moveToFirst()){
            do {
                val id = Integer.parseInt(cursor.getString(0))
                val name = cursor.getString(1)
                storeTasks.add(PreferenceModel(id,name))
            }while (cursor.moveToNext())
        }
        cursor.close()
        return storeTasks
    }

    fun addTask(taskName: String) {
        val values = ContentValues()
        values.put(COLUMN_PREF_TITLE,taskName)
        val db = this.writableDatabase
        db.insert(TABLE_PREFS,null,values)
    }

    fun deleteTask(id: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_PREFS,"$COLUMN_ID = ?", arrayOf(id.toString()))
    }

    companion object {
        private const val DATABASE_VERSION = 5
        private const val DATABASE_NAME = "task"
        private const val TABLE_PREFS = "tasks"

        private const val COLUMN_ID = "_id"
        private const val COLUMN_PREF_TITLE = "taskname"
    }
}