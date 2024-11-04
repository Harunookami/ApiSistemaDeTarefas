package com.rafaelachagas.SistemaListaTarefas.Repository;

import com.rafaelachagas.SistemaListaTarefas.Model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    Optional<TaskModel> findByTitle(String title);

    boolean existsByOrderPresentation(int orderPresentation);

    @Query("SELECT MAX(t.orderPresentation) FROM TaskModel t")
    Integer findMaxOrderPresentation();
}
