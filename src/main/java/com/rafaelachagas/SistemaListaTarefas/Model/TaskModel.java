package com.rafaelachagas.SistemaListaTarefas.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;


@Data
@Entity
@NoArgsConstructor
@Table(name = "task_model")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 200, nullable = false)
    private String title;

    @NotNull
    @Column(length = 250, nullable = false)
    private String description;

    private double cost;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate deadline;

    @Column(unique = true, name = "order_presentation")
    private int orderPresentation;

}