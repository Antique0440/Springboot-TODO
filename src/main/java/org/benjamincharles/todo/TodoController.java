package org.benjamincharles.todo;

import org.benjamincharles.todo.model.Todo;
import org.benjamincharles.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TodoController {

    private final TodoService todoService;
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @GetMapping("/")
    public String home(Model model){
        List<Todo> todos = todoService.getAllTodos();
        model.addAttribute("todos", todos);
        return "index";
    }
    @PostMapping("/todos")
    public String createTodo(
            @RequestParam String title,
            RedirectAttributes redirectAttributes
    ){
        if (title == null || title.isBlank()) {
            redirectAttributes.addFlashAttribute("error", "Please enter a title");
            return "redirect:/";
        }
        todoService.createTodo(title.trim());
        return "redirect:/";
    }
    @PostMapping("/todos/{id}/edit")
    public String updateTodo(
            @PathVariable Long id,
            @RequestParam String title

    ){
        if (title == null || title.isBlank()){
            return "redirect:/";
        }
        todoService.updateTodo(id, title);
        return "redirect:/";

    }
    @PostMapping("/todos/{id}/toggle")
    public String toggleTodo(@PathVariable Long id){
        todoService.toggleTodo(id);
        return "redirect:/";
    }


}
