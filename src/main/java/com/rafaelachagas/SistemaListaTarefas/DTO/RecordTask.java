package com.rafaelachagas.SistemaListaTarefas.DTO;

import java.time.LocalDate;

public record RecordTask(Long id, String title, double cost, String description, LocalDate deadline, int orderPresentation) {
}
