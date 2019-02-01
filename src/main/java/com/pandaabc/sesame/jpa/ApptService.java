package com.pandaabc.sesame.jpa;

import com.pandaabc.sesame.dto.Appointment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApptService {

    public List<Appointment> getAppointWithIds(List<Long> ids) {
        return new ArrayList<>();
    }

}
