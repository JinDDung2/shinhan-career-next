package com.example.todo.service;

import com.example.todo.controller.TodoRequest;
import com.example.todo.domain.Todo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TodoService {

    private final List<Todo> todoList = new CopyOnWriteArrayList<>(); // mult-thread 에 안정성
    private final AtomicLong idGen = new AtomicLong(1); // 원자성 보장

    public List<Todo> findAll() {
        return todoList;
    }

    public Optional<Todo> findById(Long id) {
        return todoList.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    public List<Todo> search(String keyword) {
        return todoList.stream()
                .filter(t -> t.getTitle().contains(keyword))
                .toList();
    }

    public Todo create(TodoRequest request) {
        if (request == null || request.title().isBlank()) {
            throw new IllegalArgumentException("제목은 필수입니다.");
        }

        Todo todo = new Todo(idGen.getAndIncrement(), request.title(), false);
        todoList.add(todo);

        return todo;
    }

    public Optional<Todo> toggleDone(Long id) {
        return findById(id).map(t -> {
                    t.setDone(!t.isDone());
                    return t;
                });
    }

    public boolean delete(Long id) {
        return todoList.removeIf(t -> t.getId().equals(id));
    }
}
