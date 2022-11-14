package getbang.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import lombok.Data;

@Entity
@Table(name="ReservationStatusView_table")
@Data
public class ReservationStatusView {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long reservationId;
        private String reservationStatus;
        private Date reservationDate;
        private Long scheduleId;
        private Integer roomId;
        private String roomName;
        private Integer cafeId;
        private String cafeName;
        private String userName;
        private String userPhone;


}