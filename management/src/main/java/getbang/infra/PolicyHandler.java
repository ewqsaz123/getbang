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
    @Autowired ScheduleRepository scheduleRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='ReservationRequested'")
    public void wheneverReservationRequested_RequestedReservation(@Payload ReservationRequested reservationRequested){

        ReservationRequested event = reservationRequested;
        System.out.println("\n\n##### listener RequestedReservation : " + reservationRequested + "\n\n");

        // Comments //
        //예약요청 폴리시
        Schedule.requestedReservation(event);

    }

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='ReservationCanceled'")
    public void wheneverReservationCanceled_CancelReservation(@Payload ReservationCanceled reservationCanceled){

        ReservationCanceled event = reservationCanceled;
        System.out.println("\n\n##### listener CancelReservation : " + reservationCanceled + "\n\n");


        // Comments // 
		//예약 취소 폴리시
        Schedule.cancelReservation(event);

    }


}


