package com.example.multithreadcodesample.domain.brand.repository;

import com.example.multithreadcodesample.domain.brand.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
