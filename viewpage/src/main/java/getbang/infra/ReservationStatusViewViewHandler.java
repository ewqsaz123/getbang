package getbang.infra;

import getbang.domain.*;
import getbang.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationStatusViewViewHandler {


    @Autowired
    private ReservationStatusViewRepository reservationStatusViewRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRoomCreated_then_CREATE_1 (@Payload RoomCreated roomCreated) {
        try {

            if (!roomCreated.validate()) return;

            // view 객체 생성
            ReservationStatusView reservationStatusView = new ReservationStatusView();
            // view 객체에 이벤트의 Value 를 set 함
            reservationStatusView.setReservationDate(roomCreated.getReservationDate());
            reservationStatusView.setScheduleId(roomCreated.getScheduleId());
            reservationStatusView.setRoomId(roomCreated.getRoomId());
            reservationStatusView.setRoomName(roomCreated.getRoomName());
            reservationStatusView.setCafeId(roomCreated.getCafeId());
            reservationStatusView.setCafeName(roomCreated.getCafeName());
            // view 레파지 토리에 save
            reservationStatusViewRepository.save(reservationStatusView);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationRequested_then_UPDATE_1(@Payload ReservationRequested reservationRequested) {
        try {
            if (!reservationRequested.validate()) return;
                // view 객체 조회

                List<ReservationStatusView> reservationStatusViewList = reservationStatusViewRepository.findByScheduleId(reservationRequested.getScheduleId());
                for(ReservationStatusView reservationStatusView : reservationStatusViewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    reservationStatusView.setReservationId(reservationRequested.getReservationId());
                    reservationStatusView.setReservationStatus(reservationRequested.getReservationStatus());
                    reservationStatusView.setUserName(reservationRequested.getUserName());
                    reservationStatusView.setUserPhone(reservationRequested.getUserPhone());
                // view 레파지 토리에 save
                reservationStatusViewRepository.save(reservationStatusView);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationCanceled_then_UPDATE_2(@Payload ReservationCanceled reservationCanceled) {
        try {
            if (!reservationCanceled.validate()) return;
                // view 객체 조회

                List<ReservationStatusView> reservationStatusViewList = reservationStatusViewRepository.findByReservationId(reservationCanceled.getReservationId());
                for(ReservationStatusView reservationStatusView : reservationStatusViewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    reservationStatusView.setReservationStatus(reservationCanceled.getReservationStatus());
                // view 레파지 토리에 save
                reservationStatusViewRepository.save(reservationStatusView);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationApproved_then_UPDATE_3(@Payload ReservationApproved reservationApproved) {
        try {
            if (!reservationApproved.validate()) return;
                // view 객체 조회

                List<ReservationStatusView> reservationStatusViewList = reservationStatusViewRepository.findByScheduleId(reservationApproved.getScheduleId());
                for(ReservationStatusView reservationStatusView : reservationStatusViewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    reservationStatusView.setReservationStatus(reservationApproved.getRoomStatus());
                // view 레파지 토리에 save
                reservationStatusViewRepository.save(reservationStatusView);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationRejected_then_UPDATE_4(@Payload ReservationRejected reservationRejected) {
        try {
            if (!reservationRejected.validate()) return;
                // view 객체 조회

                List<ReservationStatusView> reservationStatusViewList = reservationStatusViewRepository.findByScheduleId(reservationRejected.getScheduleId());
                for(ReservationStatusView reservationStatusView : reservationStatusViewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    reservationStatusView.setReservationStatus(reservationRejected.getRoomStatus());
                // view 레파지 토리에 save
                reservationStatusViewRepository.save(reservationStatusView);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

