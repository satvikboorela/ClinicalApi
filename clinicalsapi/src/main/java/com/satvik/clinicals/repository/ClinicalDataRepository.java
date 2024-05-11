package com.satvik.clinicals.repository;

import java.util.List;

import com.satvik.clinicals.model.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {

	List<ClinicalData> findByPatientIdAndComponentNameOrderByMeasuredDateTime(int patientId, String componentName);

}
