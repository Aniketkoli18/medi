package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
     String sql="select * from doctor where doctor_id=?";
     try(PreparedStatement ps= connection.prepareStatement(sql)){
      ps.setInt(1,doctorId);
      ResultSet rs= ps.executeQuery();
      if(rs.next()){
         return new Doctor(doctorId,rs.getString("full_name"),rs.getString("specialty"),rs.getString("contact_number"),rs.getString("email"),rs.getInt("years_of_experience"));
      }
     }catch(SQLException e){
      System.out.println(e);
     }
     return null;
    }

    @Override
    public void updateDoctor(Doctor doctor)throws SQLException {
       String sql="update doctor set full_name=?,specialty=?,contact_number=?,email=?,years_of_experience=? where doctor_id=?";
       try(PreparedStatement ps=connection.prepareStatement(sql)){
           ps.setString(1,doctor.getFullName());
      ps.setString(2,doctor.getSpecialty());
      ps.setString(3,doctor.getContactNumber());
      ps.setString(4,doctor.getEmail());
      ps.setInt(5,doctor.getYearsOfExperience());
      ps.setInt(6,doctor.getDoctorId());
      ps.executeUpdate();
       }
       catch(SQLException e){
         System.out.println(e);
       }
    }

    @Override
    public void deleteDoctor(int doctorId)throws SQLException {
      String sql= "delete from doctor where doctor_id=?";
      try(PreparedStatement ps=connection.prepareStatement(sql)){
         ps.setInt(1,doctorId);
         ps.executeUpdate();
      }catch(SQLException e){
         System.out.println(e);
      }
    }

    @Override
    public List<Doctor> getAllDoctors() throws SQLException{
      String sql="select * from doctor";
      List<Doctor>pat= new ArrayList<>();
      try(PreparedStatement ps= connection.prepareStatement(sql)){
         ResultSet rs= ps.executeQuery();
         while(rs.next()){
            pat.add(new Doctor(rs.getInt("doctor_id"),rs.getString("full_name"),rs.getString("specialty"),rs.getString("contact_number"),rs.getString("email"),rs.getInt("years_of_experience")));
         }
         Collections.sort(pat,Comparator.comparing(Doctor::getFullName));
         return pat;
      }
      catch(SQLException e){
         System.out.println(e);
      }
      return pat;
    }



}
