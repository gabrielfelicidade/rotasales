package br.com.rotaractsorocabaleste.rotasales.sale;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {

    @Query("SELECT s " +
            "FROM Sale s " +
            "JOIN s.seller se " +
            "JOIN s.event e " +
            "WHERE se.id = :userId " +
            "AND (COALESCE(:eventId, NULL) IS NULL OR e.id = :eventId) " +
            "AND (COALESCE(:buyer, NULL) IS NULL OR lower(s.buyer) LIKE %:buyer%) " +
            "AND (COALESCE(:donation, NULL) IS NULL OR s.donation = :donation) " +
            "AND s.active = true")
    Page<Sale> findByUserAndEventAndBuyerAndIsDonationAndIsActive(@Param("userId") final UUID userId,
                                                                  @Param("eventId") final UUID eventId,
                                                                  @Param("buyer") final String buyer,
                                                                  @Param("donation") final Boolean donation,
                                                                  final Pageable pageable);


    @Modifying
    @Query("UPDATE Sale s SET s.active = false WHERE s.id = :saleId")
    void deleteById(@Param("saleId") final UUID saleId);

}
