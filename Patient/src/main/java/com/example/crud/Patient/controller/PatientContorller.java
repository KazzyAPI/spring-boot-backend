package com.example.crud.Patient.controller;

import com.example.crud.Patient.ResourceNotFoundException;
import com.example.crud.Patient.model.Patient;
import com.example.crud.Patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/patients")
    public Patient addPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }


    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {

        return ResponseEntity.ok(patientRepository.findAll());
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable(value = "id") Integer patientId)
            throws ResourceNotFoundException {
        Patient patient = patientRepository.findById(patiendId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));
        return ResponseEntity.ok().body(patient);
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<Patient> updatePatients(@PathVariable(value = "id") Integer patientId,
                                                   @RequestBody Patient patientDetails) throws ResourceNotFoundException {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));


       patient.setName(patientDetails.getName());

        final Patient updatePatient = patientRepository.save(patient);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deletePatient(@PathVariable(value = "id") Integer patientId)
            throws ResourceNotFoundException {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));

        patientRepository.delete(patient);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}