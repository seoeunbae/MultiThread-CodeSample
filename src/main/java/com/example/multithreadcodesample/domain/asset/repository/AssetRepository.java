package com.example.multithreadcodesample.domain.asset.repository;

import com.example.multithreadcodesample.domain.asset.model.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
