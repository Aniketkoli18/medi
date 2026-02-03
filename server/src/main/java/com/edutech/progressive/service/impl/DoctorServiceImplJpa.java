package com.edutech.progressive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.repository.DoctorRepository;
import com.edutech.progressive.service.DoctorService;


@Service
public class DoctorServiceImplJpa implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public DoctorServiceImplJpa(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public DoctorServiceImplJpa() {
    }

    @Override
    public List<Doctor> getAllDoctors() throws Exception {
        return doctorRepository.findAll();
    }

    @Override
    public Integer addDoctor(Doctor doctor) throws Exception {
        Doctor savedDoctor = doctorRepository.save(doctor);
        return savedDoctor.getDoctorId();
    }

    @Override
    public Doctor getDoctorById(int doctorId) throws Exception {
        return doctorRepository.findByDoctorId(doctorId);
    }

    // ✅ SIGNATURE MUST MATCH INTERFACE
    @Override
    public void updateDoctor(Doctor doctor) throws Exception {
    Doctor existing = doctorRepository.findByDoctorId(doctor.getDoctorId());
    if (existing == null) {
        throw new Exception("Doctor not found");
    }
    doctorRepository.save(doctor);
}


    @Override
    public void deleteDoctor(int doctorId) throws Exception {
        Doctor doctor = doctorRepository.findByDoctorId(doctorId);
        if (doctor == null) {
            throw new Exception("Doctor not found");
        }
        doctorRepository.deleteById(doctorId);
    }

    @Override
    public List<Doctor> getDoctorSortedByExperience() throws Exception {
    return doctorRepository.findAllByOrderByYearsOfExperienceAsc(); // match repository method
}

}
