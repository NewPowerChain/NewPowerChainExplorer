package com.evidence.service;

import com.evidence.entity.Evidence;
import com.evidence.entity.EvidenceType;
import com.evidence.repository.EvidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class EvidenceService {

    @Autowired
    EvidenceRepository evidenceRepository;

    public Evidence createEvidence(String creatorEvidenceHash, String evidenceHash, String evidenceFileName, String evidenceFileSize, EvidenceType evidenceType){
        Evidence evidence = new Evidence(creatorEvidenceHash,evidenceHash,evidenceFileName,evidenceFileSize,evidenceType);
        return evidenceRepository.save(evidence);
    }
}
