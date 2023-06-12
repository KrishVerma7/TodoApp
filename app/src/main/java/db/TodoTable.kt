package db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.todoapp.Todo

object TodoTable{
    val TABLE_NAME = "todos"
    object Columns {
        val ID = "id"
        val TASK = "task"
        val DONE = "done"
    }

    val CMD_CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS $TABLE_NAME
        (
        ${Columns.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${Columns.TASK} TEXT,
        ${Columns.DONE} BOOLEAN
        );
        """.trimIndent()

    // Function to insert a todo item into the database
    fun insertTodo(db: SQLiteDatabase, todo: Todo) {
        val row = ContentValues()
        row.put(Columns.TASK, todo.task) // Add the task value to the ContentValues object
        row.put(Columns.DONE, todo.done) // Add the done value to the ContentValues object

        db.insert(TABLE_NAME, null, row) // Insert the row into the specified table of the database
    }

//     Function to fetch all todo items from the database
    fun getAllTodos(db: SQLiteDatabase): ArrayList<Todo> {
        val todos = ArrayList<Todo>() // Create an ArrayList to store the fetched todo items

        val c = db.query(
            TABLE_NAME,
            arrayOf(Columns.ID, Columns.TASK, Columns.DONE), // Columns to fetch from the table
            null, // Selection criteria (null for all rows)
            null, // Selection arguments
            null, // Group by
            null, // Having
            null // Order by
        )

        while (c.moveToNext()) {
            val todo = Todo(
                c.getString(1), // Get the task value from the cursor (at index 1)
                c.getInt(2) == 1 // Get the done value from the cursor (at index 2) and convert it to Boolean
            )

            todos.add(todo) // Add the todo item to the ArrayList
        }

        return todos // Return the ArrayList containing all the fetched todo items
    }

}