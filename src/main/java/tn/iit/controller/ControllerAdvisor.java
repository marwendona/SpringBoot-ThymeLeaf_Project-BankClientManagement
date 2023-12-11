package tn.iit.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tn.iit.exception.ResourceNotFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleAccountNotFoundException(ResourceNotFoundException ex, Model model) {
        model.addAttribute("timestamp", LocalDateTime.now());
        model.addAttribute("message", ex.getMessage());
        return "404";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(ResourceNotFoundException ex, Model model) {
        model.addAttribute("timestamp", LocalDateTime.now());
        model.addAttribute("message", "Generic Exception");
        return "404";
    }
}
