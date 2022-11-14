package getbang.infra;

import javax.naming.NameParser;

import javax.naming.NameParser;
import javax.transaction.Transactional;

import getbang.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import getbang.domain.*;


@Service
@Transactional
public class PolicyHandler{
    @Autowired ReservationRepository reservationRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='ReservationApproved'")
    public void wheneverReservationApproved_UpdateReservationState(@Payload ReservationApproved reservationApproved){

        ReservationApproved event = reservationApproved;
        System.out.println("\n\n##### listener UpdateReservationState : " + reservationApproved + "\n\n");


        // Comments // 
		//예약상태 업데이트 폴리시
        Reservation.updateReservationState(event);
        

        

    }
    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='ReservationRejected'")
    public void wheneverReservationRejected_UpdateReservationState(@Payload ReservationRejected reservationRejected){

        ReservationRejected event = reservationRejected;
        System.out.println("\n\n##### listener UpdateReservationState : " + reservationRejected + "\n\n");


        // Comments // 
		//예약상태 업데이트 폴리시


        Reservation.updateReservationState(event);
        

        

    }

}


