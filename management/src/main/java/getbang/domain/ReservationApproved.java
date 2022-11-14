package getbang.domain;

import getbang.domain.*;
import getbang.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ReservationApproved extends AbstractEvent {

    private Long scheduleId;
    private String roomStatus;

    public ReservationApproved(Schedule aggregate){
        super(aggregate);
    }
    public ReservationApproved(){
        super();
    }
}
