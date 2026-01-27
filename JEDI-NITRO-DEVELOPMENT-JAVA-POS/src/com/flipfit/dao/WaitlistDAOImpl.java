package com.flipfit.dao;

import com.flipfit.bean.Waitlist;
import com.flipfit.helper.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WaitlistDAOImpl implements WaitlistDAO {

    @Override
    public boolean addToWaitlist(Waitlist waitlist) {
        // Calculate the next position based on the current number of people for that slot
        int currentSize = getWaitlistSize(waitlist.getSlotId());
        String query = "INSERT INTO Waitlist (userId, slotId, position) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, waitlist.getUserId());
            stmt.setInt(2, waitlist.getSlotId());
            stmt.setInt(3, currentSize + 1);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        waitlist.setWaitlistId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeFromWaitlist(int userId, int slotId) {
        String query = "DELETE FROM Waitlist WHERE userId = ? AND slotId = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, slotId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Waitlist getWaitlistBySlot(int slotId) {
        // Fetch the first person in line (lowest position)
        String query = "SELECT * FROM Waitlist WHERE slotId = ? ORDER BY position ASC LIMIT 1";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, slotId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToWaitlist(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Waitlist> getWaitlistsByUser(int userId) {
        List<Waitlist> list = new ArrayList<>();
        String query = "SELECT * FROM Waitlist WHERE userId = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToWaitlist(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getWaitlistSize(int slotId) {
        String query = "SELECT COUNT(*) FROM Waitlist WHERE slotId = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, slotId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getUserPosition(int userId, int slotId) {
        String query = "SELECT position FROM Waitlist WHERE userId = ? AND slotId = ?";
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, slotId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getInt("position");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Waitlist promoteUser(int slotId) {
        // Find the top user and remove them (Poll logic)
        Waitlist topUser = getWaitlistBySlot(slotId);
        if (topUser != null) {
            removeFromWaitlist(topUser.getUserId(), slotId);
            return topUser;
        }
        return null;
    }

    private Waitlist mapResultSetToWaitlist(ResultSet rs) throws SQLException {
        // This requires the public Waitlist() constructor to exist
        Waitlist w = new Waitlist();
        w.setWaitlistId(rs.getInt("waitlistId"));
        w.setUserId(rs.getInt("userId"));
        w.setSlotId(rs.getInt("slotId"));
        return w;
    }
}