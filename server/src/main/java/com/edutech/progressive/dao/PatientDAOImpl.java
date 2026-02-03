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

public class PatientDAOImpl implements PatientDAO {

  private Connection connection;

  public PatientDAOImpl() {
    this.connection = DatabaseConnectionManager.getConnection();
  }

  @Override
  public int addPatient(Patient patient) throws SQLException {
    String sql = "insert into patient(full_name,date_of_birth,contact_number,email,address) values(?,?,?,?,?)";
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, patient.getFullName());
      ps.setDate(2, new java.sql.Date(patient.getDateOfBirth().getTime()));
      ps.setString(3, patient.getContactNumber());
      ps.setString(4, patient.getEmail());
      ps.setString(5, patient.getAddress());

      int n = ps.executeUpdate();
      if (n > 0) {
        rs = ps.getGeneratedKeys();
        if (rs.next()) {
          return rs.getInt(1);
        }
      }
    } catch (SQLException e) {
      throw e;
    } finally {
      if (ps != null)
        ps.close();
      if (rs != null)
        rs.close();
    }
    return -1;
  }

  @Override
  public Patient getPatientById(int patientId) throws SQLException {
    String sql = "select * from patient where patient_id=?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setInt(1, patientId);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        return new Patient(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5),
            rs.getString(6));
      }

    } catch (SQLException e) {
      throw e;
    }
    return null;
  }

  @Override
  public void updatePatient(Patient patient) throws SQLException {
    String sql = "update patient set full_name=?,date_of_birth=?,contact_number=?,email=?,address=? where patient_id=?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {

      ps.setString(1, patient.getFullName());
      ps.setDate(2,  new java.sql.Date(patient.getDateOfBirth().getTime()));
      ps.setString(3, patient.getContactNumber());
      ps.setString(4, patient.getEmail());
      ps.setString(5, patient.getAddress());
      ps.setInt(6, patient.getPatientId());

      ps.executeUpdate();

    } catch (SQLException e) {
      throw e;
    }

  }

  @Override
  public void deletePatient(int patientId) throws SQLException {
    String sql = "delete from patient where patient_id=?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
      ps.setInt(1, patientId);

      ps.executeUpdate();

    } catch (SQLException e) {
      throw e;
    }

  }

  @Override
  public List<Patient> getAllPatients() throws SQLException {
    String sql = "select * from patient";

    List<Patient> patients = new ArrayList<>();

    try (PreparedStatement ps = connection.prepareStatement(sql)) {

      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        patients.add(new Patient(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5),
            rs.getString(6)));
      }

    } catch (SQLException e) {
      throw e;
    }
    return patients;
  }

}
