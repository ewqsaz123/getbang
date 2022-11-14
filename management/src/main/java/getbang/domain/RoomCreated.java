package getbang.domain;

import getbang.domain.*;
import getbang.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class RoomCreated extends AbstractEvent {

    private Long scheduleId;
    private Integer roomId;
    private String roomName;
    private Integer cafeId;
    private String cafeName;
    private Date reservationDate;
    private String roomStatus;
    private Date createAt;
    private Date updateAt;

    public RoomCreated(Schedule aggregate){
        super(aggregate);
    }
    public RoomCreated(){
        super();
    }
}
