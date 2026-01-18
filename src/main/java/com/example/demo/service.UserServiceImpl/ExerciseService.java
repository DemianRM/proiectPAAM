package com.example.demo.service.UserServiceImpl;

import com.example.demo.dto.ExerciseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExerciseService {
    ResponseEntity<String> registerExercise(ExerciseDTO exerciseDTO, String email);
    ResponseEntity<String> deleteExercise(ExerciseDTO exerciseDTO, String date, String email);
    ResponseEntity<List<ExerciseDTO>> findExercisesForUser(String email, String date);
    ResponseEntity<List<ExerciseDTO>> getAllExercises(String email);
    ResponseEntity<List<Object[]>> getStatistics(String email);
}
