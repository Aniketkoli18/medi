package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Patient;

public class PatientDAOImpl implements PatientDAO{

private Connection connection;

    public PatientDAOImpl() {
        
          
              try {
                this.connection=DatabaseConnectionManager.getConnection();
              } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
           
       
}

    @Override
    
    public int addPatient(Patient patient)throws SQLException {
      String sql="INSERT INTO patient(full_name, date_of_birth, contact_number, email, address)VALUES(?,?,?,?,?)";
      try( PreparedStatement ps= connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
       
        ps.setString(1,patient.getFullName());
        ps.setDate(2,new Date(patient.getDateOfBirth().getTime()) );
        ps.setString(3,patient.getContactNumber());
        ps.setString(4,patient.getEmail());
        ps.setString(5,patient.getAddress());
        ps.executeUpdate();
        ResultSet rs= ps.getGeneratedKeys();
        if(rs.next()){
            patient.setPatientId(rs.getInt(1));
        }
        
      }
      return patient.getPatientId();
    }

    @Override
    public Patient getPatientById(int patientId)throws SQLException {
      String sql="select * from patient where patient_id=?";
      Patient patient= null;
      try( PreparedStatement ps= connection.prepareStatement(sql)){
       
        ps.setInt(1,patientId);
        ResultSet rs= ps.executeQuery();
        if(rs.next()){
            int npatientId= rs.getInt("patient_id");
            String fullName=rs.getString("full_name");
            Date dateOfBirth=new Date(rs.getDate("date_of_birth").getTime());
            String contactNumber= rs.getString("contact_number");
            String email=rs.getString("email");
            String address= rs.getString("address");

             patient= new Patient(npatientId, fullName, dateOfBirth, contactNumber, email, address);
        }
      }catch(SQLException e){
        System.out.println(e);
      }
      return patient;
    }

    @Override
    public void updatePatient(Patient patient)throws SQLException {
        String sql="UPDATE patient SET full_name=?, date_of_birth=?,contact_number=?,email=?,address=? where patient_id=?";
        try( PreparedStatement ps= connection.prepareStatement(sql)){
            
               ps.setString(1,patient.getFullName());
                 ps.setDate(2,new Date(patient.getDateOfBirth().getTime()) );
                 ps.setString(3,patient.getContactNumber());
                 ps.setString(4,patient.getEmail());
                 ps.setString(5,patient.getAddress());
                 ps.setInt(6,patient.getPatientId());
                 ps.executeUpdate();
        }
        
    }

    @Override
    public void deletePatient(int patientId)throws SQLException {
      String sql="delete from patient where patient_id=?";
      try(PreparedStatement ps= connection.prepareStatement(sql)){
          
          ps.setInt(1,patientId);
          ps.executeUpdate();
      }
    }

    @Override
    public List<Patient> getAllPatients()throws SQLException {
        List<Patient>patients= new ArrayList<>();
       String sql="select * from patient";
       
       try( PreparedStatement ps= connection.prepareStatement(sql)){
       
       ResultSet rs= ps.executeQuery();
       while(rs.next()){
        int patientId= rs.getInt("patient_id");
            String fullName=rs.getString("full_name");
            Date dateOfBirth=new Date(rs.getDate("date_of_birth").getTime());
            String contactNumber= rs.getString("contact_number");
            String email=rs.getString("email");
            String address= rs.getString("address");

            Patient patient= new Patient(patientId, fullName, dateOfBirth, contactNumber, email, address);
            patients.add(patient);
       }
       
       return patients;
    }
  }

}
