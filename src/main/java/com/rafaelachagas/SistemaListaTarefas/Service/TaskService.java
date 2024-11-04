package com.rafaelachagas.SistemaListaTarefas.Service;

import com.rafaelachagas.SistemaListaTarefas.DTO.RecordTask;
import com.rafaelachagas.SistemaListaTarefas.Model.TaskModel;
import com.rafaelachagas.SistemaListaTarefas.Repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {


    @Autowired
    private TaskRepository repository;

    @Transactional
    public TaskModel addTask (RecordTask task) {

        if (repository.findByTitle(task.title()).isPresent()) {
            throw new RuntimeException("Já existe uma tarefa com o título: " + task.title());
        }

        if (repository.existsByOrderPresentation(task.orderPresentation())) {
            throw new RuntimeException("Já existe uma tarefa com a ordem de apresentação: " + task.orderPresentation());
        }

        TaskModel newTask = new TaskModel();

        newTask.setTitle(task.title());
        newTask.setDescription(task.description());
        newTask.setCost(task.cost());
        newTask.setDeadline(task.deadline());
        newTask.setOrderPresentation(task.orderPresentation());

        Integer maxOrder = repository.findMaxOrderPresentation();
        newTask.setOrderPresentation(maxOrder != null ? maxOrder + 1 : 1);

        return repository.save(newTask);
    }


    public List<TaskModel> listAll() {
        return repository.findAll();
    }

    public TaskModel findTaskById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com o ID: " + id));

    }

    public TaskModel update(Long id, RecordTask taskModel) {
        TaskModel task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com o ID: " + id));

        Optional<TaskModel> existingTask = repository.findByTitle(taskModel.title());
        if (existingTask.isPresent() && !existingTask.get().getId().equals(id)) {
            throw new RuntimeException("Já existe uma tarefa com o título: " + taskModel.title() + ". Portanto, não será possível alterá-la.");
        }

        task.setDescription(taskModel.description());
        task.setCost(taskModel.cost());
        task.setDeadline(taskModel.deadline());

        return repository.save(task);
    }

    public void deleteTask(Long id) {
        TaskModel task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com o Id: " + id));
        repository.delete(task);
    }


}
