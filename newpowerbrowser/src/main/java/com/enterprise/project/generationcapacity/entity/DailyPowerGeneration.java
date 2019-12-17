package com.enterprise.project.generationcapacity.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

//日发电量
@Entity
public class DailyPowerGeneration {

    @GeneratedValue
    @Id
    Long id;
    String txId;
    String assetHash;
    Date date;
    BigDecimal generationCapacity;

    Integer flag;

    public DailyPowerGeneration() {
    }

    public DailyPowerGeneration(String txId, String assetHash, Date date, BigDecimal generationCapacity) {
        this.txId = txId;
        this.assetHash = assetHash;
        this.date = date;
        this.generationCapacity = generationCapacity;
        this.flag = 1;
    }

    public String getTxId() {
        return txId;
    }

    public String getAssetHash() {
        return assetHash;
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getGenerationCapacity() {
        return generationCapacity;
    }

    public void updateGenerationCapacity(BigDecimal generationCapacity) {
        this.generationCapacity=this.generationCapacity.add(generationCapacity);
        this.flag++;
    }

    public Integer getFlag() {
        return flag;
    }
}
