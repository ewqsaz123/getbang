package getbang.domain;

import getbang.infra.AbstractEvent;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Data
public class ReservationRequested extends AbstractEvent {

    private Long reservationId;
    private Long scheduleId;
    private String reservationStatus;
    private Date reservationDate;
    private String userName;
    private String userPhone;
}
