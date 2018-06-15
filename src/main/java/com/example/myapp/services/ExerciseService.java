package com.example.myapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/api/exercise/{exerciseId}")
	public Exercise findExerciseById(@PathVariable("exerciseId") int exerciseId) {
		Optional<Exercise> data = exerciseRepository.findById(exerciseId);
		if (data.isPresent()) {
			return data.get();
		} else {
			return null;
		}
	}

	@PostMapping("/api/exercise/search")
	public List<Exercise> saveAllExercises(@RequestBody List<Exercise> exercises) {
		ArrayList<Exercise> temp = new ArrayList<Exercise>();
		for (Exercise e : exercises) {
			Optional<Exercise> data = exerciseRepository.findById(e.getId());
			if (data.isPresent()) {
				exerciseRepository.deleteById(e.getId());
			}
			temp.add(exerciseRepository.save(e));
		}
		return temp;
	}
}
