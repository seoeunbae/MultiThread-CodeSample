package com.example.multithreadcodesample.domain.styling.repository;

import com.example.multithreadcodesample.domain.styling.model.entity.Styling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StylingRepository extends JpaRepository<Styling, Long> {
}
