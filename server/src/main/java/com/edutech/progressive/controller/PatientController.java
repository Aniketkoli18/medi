package com.edutech.progressive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.service.impl.PatientServiceImplArraylist;
import com.edutech.progressive.service.impl.PatientServiceImplJpa;



@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientServiceImplArraylist arrayListService;

    @Autowired
    private PatientServiceImplJpa jpaService;

    // JPA APIs
    @GetMapping
    public ResponseEntity<?> getAllPatients() {
        try {
            return new ResponseEntity<>(jpaService.getAllPatients(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<?> getPatientById(@PathVariable int patientId) {
        try {
            return new ResponseEntity<>(jpaService.getPatientById(patientId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> addPatient(@RequestBody Patient patient) {
        try {
            jpaService.addPatient(patient);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<?> updatePatient(@PathVariable int patientId,
                                           @RequestBody Patient patient) {
        try {
            patient.setPatientId(patientId);
            jpaService.updatePatient(patient);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   @DeleteMapping("/{patientId}")
public ResponseEntity<Void> deletePatient(@PathVariable int patientId) {
    try {
        jpaService.deletePatient(patientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Must be 204
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    // ArrayList APIs
    @GetMapping("/fromArrayList")
    public ResponseEntity<List<Patient>> getAllPatientFromArrayList() {
        return new ResponseEntity<>(arrayListService.getAllPatients(), HttpStatus.OK);
    }

    @PostMapping("/toArrayList")
    public ResponseEntity<String> addPatientToArrayList(@RequestBody Patient patient) {
        arrayListService.addPatient(patient);
        return new ResponseEntity<>("Patient Added", HttpStatus.CREATED);
    }

    @GetMapping("/fromArrayList/sorted")
    public ResponseEntity<List<Patient>> getSortedPatients() {
        return new ResponseEntity<>(arrayListService.getAllPatientSortedByName(), HttpStatus.OK);
    }
}
