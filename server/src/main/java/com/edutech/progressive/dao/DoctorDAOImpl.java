package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Doctor;

public class DoctorDAOImpl implements DoctorDAO{
   private Connection connection;
   

    public DoctorDAOImpl() {
      try {
         this.connection=DatabaseConnectionManager.getConnection();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

    @Override
    public int addDoctor(Doctor doctor)throws SQLException {
     String sql="insert into doctor(full_name,specialty,contact_number,email,years_of_experience)values(?,?,?,?,?)";
     try(PreparedStatement ps= connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
      ps.setString(1,doctor.getFullName());
      ps.setString(2,doctor.getSpecialty());
      ps.setString(3,doctor.getContactNumber());
      ps.setString(4,doctor.getEmail());
      ps.setInt(5,doctor.getYearsOfExperience());
      ps.executeUpdate();
      ResultSet rs= ps.getGeneratedKeys();
      if(rs.next()){
         doctor.setDoctorId(rs.getInt(1));
      }
     }
     return doctor.getDoctorId();
    }

    @Override
    public Doctor getDoctorById(int doctorId)throws SQLException {
       return null;
    }

    @Override
    public void updateDoctor(Doctor doctor)throws SQLException {
       
    }

    @Override
    public void deleteDoctor(int doctorId)throws SQLException {
    }

    @Override
    public List<Doctor> getAllDoctors() throws SQLException{
       return new ArrayList<>();
    }



}
