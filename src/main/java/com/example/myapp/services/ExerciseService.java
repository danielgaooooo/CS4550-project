package com.example.myapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.Exercise;
import com.example.myapp.repositories.ExerciseRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExerciseService {
	
	@Autowired
	ExerciseRepository exerciseRepository;	
	
	
	@PostMapping("/api/exercise/search")
	public void saveAllExercises(@RequestBody List<Exercise> exercises) {
		
	}
}
