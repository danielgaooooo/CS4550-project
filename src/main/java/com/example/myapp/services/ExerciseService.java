package com.example.myapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.Exercise;
import com.example.myapp.models.Workout;
import com.example.myapp.repositories.ExerciseRepository;
import com.example.myapp.repositories.WorkoutRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "true")
public class ExerciseService {

	@Autowired
	ExerciseRepository exerciseRepository;	

	@Autowired
	WorkoutRepository workoutRepository;	

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
			List<Exercise> data = (List<Exercise>) exerciseRepository.findExerciseByName(e.getDescription(), e.getName());
			if (data.size() == 0) {
				temp.add(exerciseRepository.save(e));
			} else {
				temp.add(data.get(0));
			}
		}
		return temp;
	}

	@PostMapping("/api/workout/{workoutId}/exercise/{exerciseId}")
	public Exercise addExerciseToWorkout(@PathVariable("workoutId") int workoutId, 
			@PathVariable("exerciseId") int exerciseId) {
		Optional<Workout> workoutData = this.workoutRepository.findById(workoutId);
		Optional<Exercise> exerciseData = this.exerciseRepository.findById(exerciseId);
		if (workoutData.isPresent() && exerciseData.isPresent()) {
			Workout workout = workoutData.get();
			Exercise exercise = exerciseData.get();
			exercise.setWorkout(workout);
			return this.exerciseRepository.save(exercise);
		} else {
			return null;
		}
	}
	
	@DeleteMapping("/api/exercise/{exerciseId}")
	public void deleteExercise(@PathVariable("exerciseId") int exerciseId) {
		this.exerciseRepository.deleteById(exerciseId);
	}
}
