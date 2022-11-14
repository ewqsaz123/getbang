package getbang.domain;

import getbang.config.ReservationStatus;
import getbang.domain.ReservationRequested;
import getbang.domain.ReservationCanceled;
import getbang.ReservationApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Reservation_table")
@Data

public class Reservation  {

    @Id
    @GeneratedValue
    private Long reservationId;
    private Long scheduleId;
    private String reservationStatus;
    private Date reservationDate;
    private String userName;
    private String userPhone;
    private Date createAt;
    private Date updateAt;

    /* 예약 요청됨 */
    @PostPersist
    public void onPostPersist(){

        ReservationRequested reservationRequested = new ReservationRequested(this);
        reservationRequested.publishAfterCommit();
    }

    /* 예약 취소함 */
    @PostUpdate
    public void onPostUpdate(){
        ReservationStatus status = ReservationStatus.valueOf(reservationStatus);

        if(status != null){
            switch (status){
                case CAN:                       //예약취소
                    ReservationCanceled reservationCanceled = new ReservationCanceled(this);
                    reservationCanceled.publishAfterCommit();
                    break;
                default :
                    break;
            }
        }
    }

    public static ReservationRepository repository(){
        ReservationRepository reservationRepository = ReservationApplication.applicationContext.getBean(ReservationRepository.class);
        return reservationRepository;
    }

    /* 카페사장님이 예약 승인 */
    public static void updateReservationState(ReservationApproved reservationApproved){

        repository().findByScheduleId(reservationApproved.getScheduleId()).ifPresent(reservation->{
            
            reservation.setReservationStatus(reservationApproved.getRoomStatus());
            repository().save(reservation);
         });
    }

    /* 카페사장님이 예약 거절 */
    public static void updateReservationState(ReservationRejected reservationRejected){

        repository().findById(reservationRejected.getScheduleId()).ifPresent(reservation->{
            reservation.setReservationStatus(reservationRejected.getRoomStatus());
            repository().save(reservation);
         });
    }


}
