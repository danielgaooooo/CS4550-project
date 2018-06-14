package com.example.myapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.myapp.models.Exercise;

public interface ExerciseRepository extends CrudRepository<Exercise, Integer> {

}
