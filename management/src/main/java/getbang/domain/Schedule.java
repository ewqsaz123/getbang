package getbang.domain;

import getbang.config.RoomStatus;
import getbang.domain.ReservationApproved;
import getbang.domain.ReservationRejected;
import getbang.domain.ReservationCanceled;
import getbang.domain.RoomCreated;
import getbang.ManagementApplication;
import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

@Entity
@Table(name="Schedule_table")
@Data
@Getter
@Setter
public class Schedule  {

    @Id
    @GeneratedValue
    private Long scheduleId;
    private Integer roomId;
    private String roomName;
    private Integer cafeId;
    private String cafeName;
    private Date reservationDate;
    private String roomStatus;
    private Date createAt;
    private Date updateAt;

    /* room 생성됨 */
    @PostPersist
    public void onPostPersist(){
        RoomCreated roomCreated = new RoomCreated(this);
        roomCreated.publishAfterCommit();
    }

    /* 예야 승인 또는 거부함 */
    @PostUpdate
    public void onPostUpdate(){
        RoomStatus status = RoomStatus.valueOf(roomStatus);

        switch(status){
            case APR: //예약 승인
                ReservationApproved reservationApproved = new ReservationApproved(this);
                reservationApproved.publishAfterCommit();
                break;
            case REJ: //예약 거부
                ReservationRejected reservationRejected = new ReservationRejected(this);
                reservationRejected.publishAfterCommit();
                break;
            default:
                break;
        }

    }

    public static ScheduleRepository repository(){
        ScheduleRepository scheduleRepository = ManagementApplication.applicationContext.getBean(ScheduleRepository.class);
        return scheduleRepository;
    }

    //예약취소 policy에 대한 동작
    public static void cancelReservation(ReservationCanceled reservationCanceled){
        repository().findById(reservationCanceled.getScheduleId()).ifPresent(schedule->{
            schedule.setRoomStatus(reservationCanceled.getReservationStatus());
            repository().save(schedule);
         });

    }

    //예약요청 policy에 대한 동작
    public static void requestedReservation(ReservationRequested reservationRequested){
        repository().findById(reservationRequested.getScheduleId()).ifPresent(schedule->{
            schedule.setRoomStatus(reservationRequested.getReservationStatus());
            repository().save(schedule);
         });
    }

}
