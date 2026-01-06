package com.todo.todolist.service;

import com.todo.todolist.model.Todo;
import com.todo.todolist.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Array;
import java.util.List;

@Service
public class TodoService {
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    private final TodoRepository todoRepository;

    public List<Todo> get(){
        return todoRepository.findAll();
    }
    public Todo create(Todo todo){
        return todoRepository.save(todo);
    }
    public void deleteTodo(Long id){
        if (!todoRepository.existsById(id)) {
            throw new EntityNotFoundException("Todo não encontrado");
        }
        todoRepository.deleteById(id);
    }
    public Todo getTodo(Long id){
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo não encontrado"));
    }
    public Todo updateTodo(Long id, Todo todo){
        if (!todoRepository.existsById(id)) {
            throw new RuntimeException("Todo não encontrado");
        }
        return todoRepository.findById(id)
                .map(todoItem -> {
                    todoItem.setCompleted(todo.completed);

                    return todoRepository.save(todoItem);
                })
                .orElseThrow(() -> new RuntimeException("Todo não encontrado"));
    }
}
