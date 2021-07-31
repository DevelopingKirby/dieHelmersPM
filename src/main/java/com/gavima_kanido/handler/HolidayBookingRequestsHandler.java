package com.gavima_kanido.handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gavima_kanido.models.Holiday;
import com.gavima_kanido.models.User;
import com.gavima_kanido.utils.DatabaseOperationUtil;

public class HolidayBookingRequestsHandler {

    public List<Holiday> getTeamMembersHoliday(User user) {
        List<Holiday> employees = new ArrayList<Holiday>();

        try {
            employees = DatabaseOperationUtil.getOpenHolidaysForTeam(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public int allowHolidayBooking(Holiday h) {
        int dbOpSuccessful = 0;
        try {
            dbOpSuccessful = DatabaseOperationUtil.allowHolidayBooking(h.getUserRef(), h.getStartDate(), h.getEndDate());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbOpSuccessful;
    }

    public int denyHolidayBooking(Holiday h) {
        int dbOpSuccessful = 0;
        try {
            dbOpSuccessful = DatabaseOperationUtil.denyHolidayBooking(h.getUserRef(), h.getStartDate(), h.getEndDate(), (int) h.getTotalDays());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbOpSuccessful;
    }

}
