package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.service.DoctorService;

public class DoctorServiceImplArraylist implements DoctorService {

    private static List<Doctor> doctorList = new ArrayList<>();

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorList;
    }
    @Override
    public Integer addDoctor(Doctor doctor) {
        doctorList.add(doctor);
        return doctor.getDoctorId();

    }

    @Override
    public List<Doctor> getDoctorSortedByExperience() {
      Collections.sort(doctorList);
      return doctorList;
    }
    @Override
    public Doctor getDoctorById(int doctorId) throws Exception {
        return doctorList.get(doctorId);
    }
    @Override
    public void deleteDoctor(int doctorId) throws Exception {
       deleteDoctor(doctorId);
    }

}
