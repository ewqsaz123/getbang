package getbang.domain;

import getbang.infra.AbstractEvent;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Data
@Getter
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
}
