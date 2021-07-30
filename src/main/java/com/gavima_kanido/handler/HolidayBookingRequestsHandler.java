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
            employees = DatabaseOperationUtil.getHolidaysForTeam(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

}
