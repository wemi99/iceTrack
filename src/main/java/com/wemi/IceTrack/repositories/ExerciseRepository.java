package com.wemi.IceTrack.repositories;

import com.wemi.IceTrack.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}