package getbang.domain;

import getbang.domain.*;
import getbang.infra.AbstractEvent;
import lombok.*;
import java.util.*;
@Data
@ToString
public class ReservationApproved extends AbstractEvent {

    private Long scheduleId;
    private String roomStatus;
}


