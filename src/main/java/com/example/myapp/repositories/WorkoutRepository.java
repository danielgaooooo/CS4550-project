package com.example.myapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.myapp.models.Workout;

public interface WorkoutRepository extends CrudRepository<Workout, Integer> {

}