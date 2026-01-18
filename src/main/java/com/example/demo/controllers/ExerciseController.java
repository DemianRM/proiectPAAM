package com.example.demo.controllers;

import com.example.demo.config.JwtService;
import com.example.demo.dto.ExerciseDTO;
import com.example.demo.service.UserServiceImpl.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    private final JwtService jwtService;

    @CrossOrigin
    @PostMapping("/exercise")
    public ResponseEntity<String> postExercise(@RequestBody ExerciseDTO exerciseDTO, @RequestHeader (name="Authorization") String token){
        String email = jwtService.extractUsername(token.substring(7));
        return exerciseService.registerExercise(exerciseDTO, email);
    }

    @CrossOrigin
    @DeleteMapping("/exercise")
    public ResponseEntity<String> deleteExercise(@RequestParam String date, @RequestHeader (name="Authorization") String token, @RequestBody ExerciseDTO exerciseDTO){
        String email = jwtService.extractUsername(token.substring(7));
        return exerciseService.deleteExercise(exerciseDTO, date, email);
    }


    @GetMapping("/exercises")
    public ResponseEntity<List<ExerciseDTO>> getExercisesForUserForGivenDate(@RequestParam String date, @RequestHeader (name="Authorization") String token){
        String email = jwtService.extractUsername(token.substring(7));
        return exerciseService.findExercisesForUser(email, date);
    }

    @GetMapping("/exercise/all")
    public ResponseEntity<List<ExerciseDTO>> getAllExercises(@RequestHeader (name="Authorization") String token){
        String email = jwtService.extractUsername(token.substring(7));
        return exerciseService.getAllExercises(email);
    }

    @GetMapping("exercise/statistics")
    public ResponseEntity<List<Object[]>> getAggregate(@RequestHeader (name="Authorization") String token) {
        String email = jwtService.extractUsername(token.substring(7));
        return exerciseService.getStatistics(email);
    }



}
