package org.benjamincharles.todo;

import org.benjamincharles.todo.model.Todo;
import org.benjamincharles.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

}
