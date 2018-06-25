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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.User;
import com.example.myapp.models.Workout;
import com.example.myapp.repositories.UserRepository;
import com.example.myapp.repositories.WorkoutRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "true")
public class WorkoutService {

	@Autowired
	WorkoutRepository workoutRepository;

	@Autowired
	UserRepository userRepository;


	@DeleteMapping("/api/workout/{workoutId}")
	public void deleteWorkout(@PathVariable("workoutId") int workoutId) {
		this.workoutRepository.deleteById(workoutId);
	}


	@PostMapping("/api/workout/{userId}")
	public Workout saveWorkouts(@PathVariable("userId") int userId, 
			@RequestBody Workout newWorkout) {
		Optional<User> data = this.userRepository.findById(userId);
		if (data.isPresent()) {
			User user = data.get();
			newWorkout.setUser(user);
			return this.workoutRepository.save(newWorkout);
		} else {
			return null;
		}
	}
	

	@PutMapping("/api/workout/{workoutId}")
	public Workout updateWorkout(@PathVariable("workoutId") int workoutId, @RequestBody Workout newWorkout) {
		Optional<Workout> data = this.workoutRepository.findById(workoutId);
		if (data.isPresent()) {
			Workout workout = data.get();
			if (newWorkout.getName() != null) {
				workout.setName(newWorkout.getName());
			}
			return this.workoutRepository.save(workout);
		} else {
			return null;
		}
	}

	@GetMapping("/api/workout/user/{userId}")
	public List<Workout> findAllWorkoutsForUser(@PathVariable("userId") int userId) {
		Optional<User> data = this.userRepository.findById(userId);
		if (data.isPresent()) {
			User user = data.get();
			return user.getWorkouts();
		} else {
			return null;
		}
	}
	
	@PostMapping("/api/user/{userId}/workout")
	public Workout addWorkoutToUser(@PathVariable("userId") int userId, @RequestBody Workout workout) {
		Optional<User> userData = this.userRepository.findById(userId);
		if (userData.isPresent()) {
			User user = userData.get();
			workout.setUser(user);
			return this.workoutRepository.save(workout);
		} else {
			return null;
		}
	}
}
