package com.example.todo_list_1;

public class Todo {
    private int id;
    private String todoItem;
    private String todoDeadline;

    public Todo(int newId, String newTodoItem, String newTodoDeadline){
        setID(newId);
        setTodoItem(newTodoItem);
        setTodoDeadline(newTodoDeadline);
    }

    private void setTodoItem(String newTodoItem) {
        todoItem = newTodoItem;
    }

    private void setID(int newId) {
        id = newId;
    }

    private void setTodoDeadline(String newTodoDeadline){todoDeadline = newTodoDeadline;}

    public int getId(){return id;}

    public String getTodoItem(){return todoItem;}

    public String getTodoDeadline(){return  todoDeadline;}

    public String toString(){return id + "; " + todoItem + "; " + todoDeadline;}

}
