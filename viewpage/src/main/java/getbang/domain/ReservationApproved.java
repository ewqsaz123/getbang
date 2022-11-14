package getbang.domain;

import getbang.infra.AbstractEvent;
import lombok.Data;
import lombok.Getter;

import java.util.*;

@Data
public class ReservationApproved extends AbstractEvent {

    private Long scheduleId;
    private String roomStatus;
}
