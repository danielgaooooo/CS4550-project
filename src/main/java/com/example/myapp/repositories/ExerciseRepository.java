package com.example.myapp.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.myapp.models.Exercise;

public interface ExerciseRepository extends CrudRepository<Exercise, Integer> {
	@Query("SELECT e FROM Exercise e WHERE e.description=:content AND e.name=:name")
	Iterable<Exercise> findExerciseByName(
			@Param("content") String content,
			@Param("name") String name);
}
