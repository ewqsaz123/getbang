package getbang.infra;

import getbang.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource(collectionResourceRel="reservationStatusViews", path="reservationStatusViews")
public interface ReservationStatusViewRepository extends PagingAndSortingRepository<ReservationStatusView, Long> {

    List<ReservationStatusView> findByScheduleId(Long scheduleId);
    List<ReservationStatusView> findByReservationId(Long reservationId);


    
}
