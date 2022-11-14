package getbang.domain;

import getbang.domain.*;
import getbang.infra.AbstractEvent;
import lombok.*;
import java.util.*;
@Data
@ToString
public class ReservationRequested extends AbstractEvent {

    private Long reservationId;
    private Long scheduleId;
    private String reservationStatus;
    private Date reservationDate;
    private String userName;
    private String userPhone;
}


