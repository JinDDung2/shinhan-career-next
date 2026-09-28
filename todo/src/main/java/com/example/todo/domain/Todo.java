package com.example.todo.domain;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Todo {
    private Long id;
    private String title;
    private boolean done;
}
