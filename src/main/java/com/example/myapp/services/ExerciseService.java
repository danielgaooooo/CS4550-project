package com.example.myapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.repositories.ExerciseRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExerciseService {
	
	@Autowired
	ExerciseRepository exerciseRepository;	
	
}
