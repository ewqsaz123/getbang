package getbang.domain;

import getbang.domain.*;
import getbang.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ReservationRejected extends AbstractEvent {

    private Long scheduleId;
    private String roomStatus;

    public ReservationRejected(Schedule aggregate){
        super(aggregate);
    }
    public ReservationRejected(){
        super();
    }
}
