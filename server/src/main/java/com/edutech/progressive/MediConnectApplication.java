package com.edutech.progressive;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.dao.PatientDAOImpl;
import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.service.PatientService;
import com.edutech.progressive.service.impl.PatientServiceImplJdbc;


@SpringBootApplication
public class MediConnectApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(MediConnectApplication.class, args);

       

    }
}
