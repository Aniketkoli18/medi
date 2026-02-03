package com.edutech.progressive;

import com.edutech.progressive.dao.DoctorDAOImpl;
import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.service.impl.DoctorServiceImplJdbc;

public class MediConnectApplication {
    public static void main(String[] args) {
        System.out.println("Welcome to MediConnect Progressive Project!");
        DoctorDAOImpl di=new DoctorDAOImpl();
        DoctorServiceImplJdbc dj=new DoctorServiceImplJdbc(di);

        Doctor d1=new Doctor(1, "ash", "low", "9777788", "as@getEmail", 100);
        Doctor d2=new Doctor(2, "adada", "asasfa", "97asfasf8", "aasfmail", 300);
        dj.addDoctor(d1);
        dj.addDoctor(d2);

        for(Doctor d: dj.getAllDoctors()){
            System.out.println(d);
        }
        System.out.println();
        dj.deleteDoctor(46);
        dj.getDoctorSortedByExperience();

        for(Doctor d: dj.getAllDoctors()){
            System.out.println(d);
        }
    }
}
