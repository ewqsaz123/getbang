package getbang.domain;

import getbang.domain.*;
import getbang.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ReservationRequested extends AbstractEvent {

    private Long reservationId;
    private Long scheduleId;
    private String reservationStatus;
    private Date reservationDate;
    private String userName;
    private String userPhone;

    public ReservationRequested(Reservation aggregate){
        super(aggregate);
    }
    public ReservationRequested(){
        super();
    }
}
