package br.com.rotaractsorocabaleste.rotasales.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("SELECT e " +
            "FROM Event e " +
            "JOIN e.institution i " +
            "WHERE i.id = :institutionId " +
            "AND ((current_date >= e.startSales " +
            "AND current_date <= e.endSales) " +
            "OR ((e.salesStrategy = 'BOTH' " +
            "OR e.salesStrategy = 'ON_SITE') " +
            "AND DATE(current_date) = DATE(e.date)))")
    Set<Event> findByInstitutionIdAndActive(@Param("institutionId") final UUID institutionId);

}
