package com.satvik.clinicals.restcontrollers;

import java.util.List;

import com.satvik.clinicals.model.ClinicalData;
import com.satvik.clinicals.model.Patient;
import com.satvik.clinicals.repository.ClinicalDataRepository;
import com.satvik.clinicals.repository.PatientRepository;
import com.satvik.clinicals.restcontrollers.dto.ClinicalDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClinicalController {

	private ClinicalDataRepository repository;
	private PatientRepository patientRepository;

	@Autowired
	ClinicalController(ClinicalDataRepository repository, PatientRepository patientRepository) {
		this.repository = repository;
		this.patientRepository = patientRepository;
	}

	@RequestMapping(value = "/clinicals", method = RequestMethod.POST)
	public ClinicalData saveClinicalData(@RequestBody ClinicalDataRequest clinicalDataRequest) {
		Patient patient = patientRepository.findById(clinicalDataRequest.getPatientId()).get();
		ClinicalData data = new ClinicalData();
		data.setComponentName(clinicalDataRequest.getComponentName());
		data.setComponentValue(clinicalDataRequest.getComponentValue());
		data.setPatient(patient);
		return repository.save(data);
	}

	@RequestMapping(value = "/clinicals/{patientId}/{componentName}", method = RequestMethod.GET)
	public List<ClinicalData> getClinicalData(@PathVariable("patientId") int patientId,
			@PathVariable("componentName") String componentName) {
		return repository.findByPatientIdAndComponentNameOrderByMeasuredDateTime(patientId, componentName);
	}

}
