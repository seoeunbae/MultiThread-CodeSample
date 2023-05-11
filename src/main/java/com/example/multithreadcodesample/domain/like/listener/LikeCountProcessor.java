package com.example.multithreadcodesample.domain.like.listener;

import com.example.multithreadcodesample.domain.asset.repository.AssetRepository;
import com.example.multithreadcodesample.domain.brand.repository.BrandRepository;
import com.example.multithreadcodesample.domain.like.model.entity.Like;
import com.example.multithreadcodesample.domain.styling.model.entity.Styling;
import com.example.multithreadcodesample.domain.styling.repository.StylingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@RequiredArgsConstructor
public class LikeCountProcessor {
    private final AssetRepository assetRepository;
    private final StylingRepository stylingRepository;
    private final BrandRepository brandRepository;

    public void increment(long targetId, Like.TargetType targetType){
        changeLikeCount(targetId, targetType, 1);
    }

    public void decrement(long targetId, Like.TargetType targetType){
        changeLikeCount(targetId, targetType, -1);
    }

    private void changeLikeCount(long targetId, Like.TargetType targetType, int changeLikeValue) {
        switch (targetType) {
            case ASSET:
                assetRepository.findById(targetId)
                        .ifPresent(assetPrototype -> {

                            assetPrototype.setLikeNumber(new AtomicInteger(assetPrototype.getLikeNumber()).addAndGet(changeLikeValue));
                            assetRepository.save(assetPrototype);
                        });
                break;
            case STYLING:
                stylingRepository.findById(targetId)
                        .ifPresent(styling -> {
                            styling.setLikeNumber(new AtomicInteger(styling.getLikeNumber()).addAndGet(changeLikeValue));
                            stylingRepository.save(styling);
                        });
                break;
            case BRAND:
                brandRepository.findById(targetId)
                        .ifPresent(brand -> {
                            brand.setLikeNumber(new AtomicInteger(brand.getLikeNumber()).addAndGet(changeLikeValue));
                            brandRepository.save(brand);
                        });
                break;
            default:
                log.warn("Not support like count. target type: {}", targetType.name());
        }
    }
}
