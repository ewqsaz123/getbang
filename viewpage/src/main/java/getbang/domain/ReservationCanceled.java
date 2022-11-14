package getbang.domain;

import getbang.infra.AbstractEvent;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.*;

@Data
public class ReservationCanceled extends AbstractEvent {

    private Long reservationId;
    private Long scheduleId;
    private String reservationStatus;
    private Date reservationDate;

}
