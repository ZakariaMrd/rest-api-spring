package com.in28minutes.rest.webservices.restfulwebservices.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoResource {
    private TodoService todoService;
    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
    }
    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username){
        return todoService.findByUsername(username);
    }
    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodoById(@PathVariable String username, @PathVariable int id){
        return todoService.findById(id);
    }
    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable String username, @PathVariable int id){
        todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/users/{username}/todos")
    public ResponseEntity<Void> addTodo(@PathVariable String username, @RequestBody Todo todo){
        Todo createdTodo = todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
        return ResponseEntity.created(null).build();
    }
    @PutMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> updateTodoById(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo){
        todoService.updateTodo(todo);
        return ResponseEntity.noContent().build();
    }
}
