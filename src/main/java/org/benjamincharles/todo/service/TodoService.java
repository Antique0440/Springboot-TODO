package org.benjamincharles.todo.service;

import org.benjamincharles.todo.model.Todo;
import org.benjamincharles.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService{
    private final TodoRepository todoRepository;
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    public List <Todo> getAllTodos(){
        return todoRepository.findAll();
    }
    public Todo createTodo(String title){
        Todo todo = new Todo(title);
        return todoRepository.save(todo);
    }
    public void updateTodo(Long id,String title){
        Todo todo = todoRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Todo not found"));
        todo.setTitle(title.trim());
        todoRepository.save(todo);
    }
    public void toggleTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found"));

        todo.setCompleted(!todo.isCompleted());
        todoRepository.save(todo);
    }
}
