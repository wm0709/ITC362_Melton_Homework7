package com.example.todo_list_1;

public class Todo {
    private int id;
    private String todoItem;

    public Todo(int newId, String newTodoItem){
        setID(newId);
        setTodoItem(newTodoItem);
    }

    private void setTodoItem(String newTodoItem) {
        todoItem = newTodoItem;
    }

    private void setID(int newId) {
        id = newId;
    }

    public int getId(){return id;}
    public String getTodoItem(){return todoItem;}

    public String toString(){return id + "; " + todoItem;}
}
