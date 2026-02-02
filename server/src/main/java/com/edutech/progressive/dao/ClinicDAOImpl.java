package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Clinic;

public class ClinicDAOImpl implements ClinicDAO{

   private Connection connection;

   
    public ClinicDAOImpl() {
      try {
         this.connection=DatabaseConnectionManager.getConnection();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

    @Override
    public int addClinic(Clinic clinic)throws SQLException {
      String sql="insert into clinic(clinic_name,location,doctor_id,contact_number,established_year)values(?,?,?,?,?)";
      try(PreparedStatement ps=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
         ps.setString(1,clinic.getClinicName());
         ps.setString(2,clinic.getLocation());
         ps.setInt(3,clinic.getDoctorId());
         ps.setString(4,clinic.getContactNumber());
         ps.setInt(5,clinic.getEstablishedYear());
         ps.executeUpdate();
         ResultSet rs=ps.getGeneratedKeys();
         if(rs.next()){
            clinic.setClinicId(rs.getInt(1));
         }
      }
      return clinic.getClinicId();
    }

    @Override
    public Clinic getClinicById(int clinicId)throws SQLException {
      String sql="select * from clinic where clinic_id=?";
      try(PreparedStatement ps= connection.prepareStatement(sql)){
         ps.setInt(1,clinicId);
         ResultSet rs= ps.executeQuery();
         if(rs.next()){
            return new Clinic(clinicId, rs.getString("clinic_name"), rs.getString("location"), rs.getInt("doctor_id"), rs.getString("contact_number"), rs.getInt("established_year"));
         }

      }
      return null;
    }

    @Override
    public void updateClinic(Clinic clinic)throws SQLException {
      String sql="update clinic set clinic_name=?,location=?,doctor_id=?,contact_number=?,established_year=?,where clinic_id=?";
      try(PreparedStatement ps= connection.prepareStatement(sql)){
           ps.setString(1,clinic.getClinicName());
         ps.setString(2,clinic.getLocation());
         ps.setInt(3,clinic.getDoctorId());
         ps.setString(4,clinic.getContactNumber());
         ps.setInt(5,clinic.getEstablishedYear());
         ps.setInt(6,clinic.getClinicId());
         ps.executeUpdate();
         
      }
       
    }

    @Override
    public void deleteClinic(int clinicId)throws SQLException {
      String sql="delete from clinic where clinic_id=?";
      try(PreparedStatement ps= connection.prepareStatement(sql)){
         ps.setInt(1,clinicId);
         ps.executeUpdate();
      }
       
    }

    @Override
    public List<Clinic> getAllClinics()throws SQLException {
       String sql="select * from clinic";
       List<Clinic>pat=new ArrayList<>();
       try(PreparedStatement ps=connection.prepareStatement(sql)){
         ResultSet rs=ps.executeQuery();
         while(rs.next()){
            pat.add(new Clinic(rs.getInt("clinic_id"),rs.getString("clinic_name"),rs.getString("location"),rs.getInt("doctor_id"),rs.getString("contact_number"),rs.getInt("established_year")));
         }
         return pat;
       }
    }
}
