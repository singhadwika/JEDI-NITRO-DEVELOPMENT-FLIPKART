package com.flipfit.dao;

import com.flipfit.bean.Waitlist;

import java.util.*;

public class WaitlistDAOImpl implements WaitlistDAO {

    // SlotId -> Queue of waitlist
    private static Map<Integer, Queue<Waitlist>> waitlistMap = new HashMap<>();

    private static int waitlistCounter = 1;

    @Override
    public boolean addToWaitlist(Waitlist waitlist) {

        waitlist.setWaitlistId(waitlistCounter++);

        Queue<Waitlist> queue =
                waitlistMap.computeIfAbsent(waitlist.getSlotId(), k -> new LinkedList<>());

        return queue.add(waitlist);
    }

    @Override
    public boolean removeFromWaitlist(int userId, int slotId) {

        Queue<Waitlist> queue = waitlistMap.get(slotId);

        if (queue == null) return false;

        return queue.removeIf(w -> w.getUserId() == userId);
    }

    @Override
    public Waitlist getWaitlistBySlot(int slotId) {

        Queue<Waitlist> queue = waitlistMap.get(slotId);

        if (queue == null || queue.isEmpty()) return null;

        return queue.peek();
    }

    @Override
    public List<Waitlist> getWaitlistsByUser(int userId) {

        List<Waitlist> result = new ArrayList<>();

        for (Queue<Waitlist> queue : waitlistMap.values()) {
            for (Waitlist w : queue) {
                if (w.getUserId() == userId) {
                    result.add(w);
                }
            }
        }

        return result;
    }

    @Override
    public int getWaitlistSize(int slotId) {

        Queue<Waitlist> queue = waitlistMap.get(slotId);

        return queue == null ? 0 : queue.size();
    }

    @Override
    public int getUserPosition(int userId, int slotId) {

        Queue<Waitlist> queue = waitlistMap.get(slotId);

        if (queue == null) return -1;

        int position = 1;

        for (Waitlist w : queue) {
            if (w.getUserId() == userId) {
                return position;
            }
            position++;
        }

        return -1;
    }

    @Override
    public Waitlist promoteUser(int slotId) {

        Queue<Waitlist> queue = waitlistMap.get(slotId);

        if (queue == null || queue.isEmpty()) return null;

        return queue.poll(); // FIFO
    }
}
