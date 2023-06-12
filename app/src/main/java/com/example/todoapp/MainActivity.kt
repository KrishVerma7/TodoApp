package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import db.MyDbHelper
import db.TodoTable

class MainActivity : AppCompatActivity() {
    val todos = ArrayList<Todo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var lvTodos:ListView=findViewById(R.id.lvTodos)
        var btnAddTodo:Button=findViewById(R.id.btnAddTodo)
        var etNewTodo:EditText=findViewById(R.id.etNewTodo)

        val todoAdapter = ArrayAdapter<Todo>(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            todos
        )
        val db = MyDbHelper(this).writableDatabase

        fun refreshTodoList(){
            val todoList = TodoTable.getAllTodos(db)
            todos.clear()
            todos.addAll(todoList)
            todoAdapter.notifyDataSetChanged()
        }
        lvTodos.adapter=todoAdapter
        refreshTodoList()

        btnAddTodo.setOnClickListener{
            val newTodo = Todo(
                etNewTodo.text.toString(),
                false
            )
            TodoTable.insertTodo(db,newTodo)
            refreshTodoList()
        }

    }
}