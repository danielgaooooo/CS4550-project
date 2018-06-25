package com.example.myapp.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Exercise {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	
	@Lob 
	@Column(name="CONTENT", length=512)
	private String description;
	private String muscle;

	@ManyToOne
	@JsonIgnore
	private Workout workout;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMuscle() {
		return muscle;
	}
	public void setMuscle(String muscle) {
		this.muscle = muscle;
	}
	public Workout getWorkout() {
		return workout;
	}
	public void setWorkout(Workout workout) {
		this.workout = workout;
	}
}
