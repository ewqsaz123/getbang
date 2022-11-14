package getbang.domain;

import getbang.domain.*;
import getbang.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ReservationCanceled extends AbstractEvent {

    private Long reservationId;
    private Long scheduleId;
    private String reservationStatus;
    private Date reservationDate;

}
