package db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDbHelper(context: Context) : SQLiteOpenHelper(
    context,
    "todos.db", // The name of the database file
    null,
    1 // The version number of the database
) {
    override fun onCreate(db: SQLiteDatabase?) {
        // This method is called when the database is created for the first time

        db?.execSQL(TodoTable.CMD_CREATE_TABLE) // Execute the SQL command to create the todo table
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        // This method is called when the database needs to be upgraded, but we are not implementing any logic here
        // Leaving it blank for now
    }
}
