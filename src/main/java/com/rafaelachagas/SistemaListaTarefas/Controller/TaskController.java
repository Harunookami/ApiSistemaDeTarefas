package com.rafaelachagas.SistemaListaTarefas.Controller;


import com.rafaelachagas.SistemaListaTarefas.DTO.RecordTask;
import com.rafaelachagas.SistemaListaTarefas.Model.TaskModel;
import com.rafaelachagas.SistemaListaTarefas.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService service;


    @PostMapping
    public TaskModel addTask(@RequestBody RecordTask task){
        return service.addTask(task);
    }

    @GetMapping
    public List<TaskModel> listTasks(){
        return service.listAll();
    }

    @GetMapping("/{id}")
    public TaskModel findTaskById(Long id){
        TaskModel task = service.findTaskById(id);
        if (task == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada.");
        }

        return service.findTaskById(id);
    }

    @PutMapping("/{id}")
    public TaskModel updateTask(@PathVariable Long id, @RequestBody RecordTask task){
        return service.update(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        service.deleteTask(id);
    }



}
