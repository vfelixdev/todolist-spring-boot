package com.todo.todolist.controller;

import com.todo.todolist.model.Todo;
import com.todo.todolist.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@CrossOrigin(
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
public class TodoController {

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    public TodoService todoService;

    @GetMapping
    public List<Todo> get(){
        return this.todoService.get();
    }

    @GetMapping("{id}")
    public Todo show(@PathVariable Long id){
        return this.todoService.getTodo(id);
    }
    @PostMapping
    public Todo create(@RequestBody Todo todo){
        return this.todoService.create(todo);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        this.todoService.deleteTodo(id);
    }
    @PutMapping("{id}")
    public Todo pupdate(@PathVariable Long id, @RequestBody Todo todo){
        return this.todoService.updateTodo(id, todo);
    }
}
