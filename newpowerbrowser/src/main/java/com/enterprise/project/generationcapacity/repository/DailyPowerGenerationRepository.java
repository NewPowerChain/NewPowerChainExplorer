package com.enterprise.project.generationcapacity.repository;

import com.enterprise.project.generationcapacity.entity.DailyPowerGeneration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Date;

public interface DailyPowerGenerationRepository extends JpaRepository<DailyPowerGeneration,Long> {
    DailyPowerGeneration findByAssetHashAndDate(String assetHash, Date date);

    @Query(value ="select sum(installedCapacity) from Project ")
    BigDecimal getSumOfInstalledCapacity();

    @Query(value = "select sum(generationCapacity) from DailyPowerGeneration where date = ?1")
    BigDecimal getOneDayPowerGeneration(Date date);
}
