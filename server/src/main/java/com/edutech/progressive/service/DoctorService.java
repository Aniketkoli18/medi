package com.edutech.progressive.service;

import com.edutech.progressive.dto.DoctorDTO;
import com.edutech.progressive.entity.Doctor;

import java.util.List;

public interface DoctorService {

    List<Doctor> getAllDoctors() throws Exception;

    Integer addDoctor(Doctor doctor) throws Exception;  // return Doctor object

    Doctor getDoctorById(int doctorId) throws Exception;

   default void updateDoctor(Doctor doctor) throws Exception{} // takes Doctor object only

    void deleteDoctor(int doctorId) throws Exception;

    List<Doctor> getDoctorSortedByExperience() throws Exception;

    default void emptyArrayList() {}

    default void modifyDoctorDetails(DoctorDTO doctorDTO) {}
}
