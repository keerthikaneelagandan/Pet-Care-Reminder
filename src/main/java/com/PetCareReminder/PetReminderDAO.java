package com.PetCareReminder;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PetReminderDAO {

    // CREATE
    public int addReminder(PetReminder r) {
        String sql = "INSERT INTO reminders (pet_name, pet_type, reminder_type, reminder_date) VALUES (?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, r.getPetName());
            ps.setString(2, r.getPetType());
            ps.setString(3, r.getReminderType());
            ps.setDate(4, Date.valueOf(r.getReminderDate())); // LocalDate -> SQL Date
            int rows = ps.executeUpdate();

            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) return rs.getInt(1); // new id
                }
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException("addReminder failed: " + e.getMessage(), e);
        }
    }

    // READ all
    public List<PetReminder> getAllReminders() {
        String sql = "SELECT pet_id, pet_name, pet_type,reminder_type, reminder_date FROM reminders ORDER BY reminder_date";
        List<PetReminder> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("pet_id");
                String name = rs.getString("pet_name");
                String type = rs.getString("pet_type");
                String Type = rs.getString("reminder_type");
                LocalDate date = rs.getDate("reminder_date").toLocalDate();
                list.add(new PetReminder(id, name, type, Type, date));
            }
        } catch (SQLException e) {
            throw new RuntimeException("getAllReminders failed: " + e.getMessage(), e);
        }
        return list;
    }

    // READ one
    public PetReminder getById(int id) {
        String sql = "SELECT pet_id, pet_name, pet_type, reminder_type, reminder_date FROM reminders WHERE pet_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("pet_name");
                    String type = rs.getString("pet_type");
                    String Type = rs.getString("reminder_type");
                    LocalDate date = rs.getDate("reminder_date").toLocalDate();
                    return new PetReminder(id, name, type, Type, date);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("getById failed: " + e.getMessage(), e);
        }
        return null;
    }

    // UPDATE
    public boolean updateReminder(PetReminder r) {
        String sql = "UPDATE reminders SET pet_name=?, pet_type=?,reminder_type, reminder_date=? WHERE pet_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, r.getPetName());
            ps.setString(2, r.getPetType());
            ps.setString(3, r.getReminderType());
            ps.setDate(4, Date.valueOf(r.getReminderDate()));
            ps.setInt(5, r.getPetId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("updateReminder failed: " + e.getMessage(), e);
        }
    }

    // DELETE
    public boolean deleteReminder(int id) {
        String sql = "DELETE FROM reminders WHERE pet_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("deleteReminder failed: " + e.getMessage(), e);
        }
    }
}
