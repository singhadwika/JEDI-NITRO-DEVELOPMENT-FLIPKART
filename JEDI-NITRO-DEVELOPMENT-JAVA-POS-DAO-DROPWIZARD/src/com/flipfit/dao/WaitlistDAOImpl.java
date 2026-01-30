package com.flipfit.dao;

import com.flipfit.bean.Waitlist;
import com.flipfit.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WaitlistDAOImpl implements WaitlistDAO {

    @Override
    public boolean addToWaitlist(Waitlist waitlist) {
        String sql = "INSERT INTO Waitlist (user_id, slot_id, request_time) VALUES (?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, waitlist.getUserId());
            pstmt.setInt(2, waitlist.getSlotId());
            pstmt.setTimestamp(3, Timestamp.valueOf(waitlist.getRequestTime()));
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        waitlist.setWaitlistId(generatedKeys.getInt(1));
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error adding to waitlist: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean removeFromWaitlist(int userId, int slotId) {
        String sql = "DELETE FROM Waitlist WHERE user_id = ? AND slot_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            pstmt.setInt(2, slotId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error removing from waitlist: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Waitlist getWaitlistBySlot(int slotId) {
        String sql = "SELECT * FROM Waitlist WHERE slot_id = ? ORDER BY request_time ASC LIMIT 1";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, slotId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToWaitlist(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting waitlist by slot: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Waitlist> getWaitlistsByUser(int userId) {
        List<Waitlist> waitlists = new ArrayList<>();
        String sql = "SELECT * FROM Waitlist WHERE user_id = ? ORDER BY request_time ASC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    waitlists.add(mapResultSetToWaitlist(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting waitlists by user: " + e.getMessage());
        }
        return waitlists;
    }

    @Override
    public int getWaitlistSize(int slotId) {
        String sql = "SELECT COUNT(*) as count FROM Waitlist WHERE slot_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, slotId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting waitlist size: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public int getUserPosition(int userId, int slotId) {
        String sql = "SELECT COUNT(*) + 1 as position FROM Waitlist " +
                     "WHERE slot_id = ? AND request_time < " +
                     "(SELECT request_time FROM Waitlist WHERE user_id = ? AND slot_id = ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, slotId);
            pstmt.setInt(2, userId);
            pstmt.setInt(3, slotId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("position");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting user position: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public Waitlist promoteUser(int slotId) {
        // Get the first user in the waitlist (FIFO)
        Waitlist waitlist = getWaitlistBySlot(slotId);
        
        if (waitlist != null) {
            // Remove from waitlist
            removeFromWaitlist(waitlist.getUserId(), slotId);
            return waitlist;
        }
        return null;
    }

    private Waitlist mapResultSetToWaitlist(ResultSet rs) throws SQLException {
        Waitlist waitlist = new Waitlist(rs.getInt("user_id"), rs.getInt("slot_id"));
        waitlist.setWaitlistId(rs.getInt("waitlist_id"));
        waitlist.setRequestTime(rs.getTimestamp("request_time").toLocalDateTime());
        return waitlist;
    }
}
