package com.healthconnect.user.model.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.healthconnect.user.model.core.User;
import com.healthconnect.user.model.physician.Physician;
import com.healthconnect.user.model.physician.PhysicianDto;

public class PhysicianConverter {

    public static Physician convertToPhysician(PhysicianDto physicianDto, User user, Physician physician) {
        if(physician == null) {
            physician = new Physician();
            physician.setCreatedOn(LocalDateTime.now());
            physician.setCreatedBy(user);
        }else {
            physician.setLastModifiedOn(LocalDateTime.now());
            physician.setLastModifiedBy(user);
        }
        physician.setDob(LocalDate.parse(physicianDto.getDob()));
        physician.setAge(LocalDate.now().getYear() - physician.getDob().getYear());
        return physician;
    }
}
