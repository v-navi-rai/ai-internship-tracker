package com.vaishnavi.backend.controller;

import com.vaishnavi.backend.entity.Application;
import com.vaishnavi.backend.repository.ApplicationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationRepository repository;

    public ApplicationController(ApplicationRepository repository) {
        this.repository = repository;
    }

    // Add application
    @PostMapping
    public Application addApplication(@RequestBody Application application) {
        return repository.save(application);
    }

    // View all applications
    @GetMapping
    public List<Application> getAllApplications() {
        return repository.findAll();
    }
}
