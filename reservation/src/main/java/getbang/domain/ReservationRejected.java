package getbang.domain;

import getbang.domain.*;
import getbang.infra.AbstractEvent;
import lombok.*;
import java.util.*;
@Data
@ToString
public class ReservationRejected extends AbstractEvent {

    private Long scheduleId;
    private String roomStatus;
}


