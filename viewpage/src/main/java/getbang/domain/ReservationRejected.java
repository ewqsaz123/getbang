package getbang.domain;

import getbang.infra.AbstractEvent;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Data
public class ReservationRejected extends AbstractEvent {

    private Long scheduleId;
    private String roomStatus;
}
