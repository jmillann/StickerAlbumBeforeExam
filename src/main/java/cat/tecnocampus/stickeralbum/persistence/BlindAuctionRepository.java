package cat.tecnocampus.stickeralbum.persistence;

import cat.tecnocampus.stickeralbum.application.outputDTOs.BlindAuctionDTO;
import cat.tecnocampus.stickeralbum.domain.BlindAuction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlindAuctionRepository extends JpaRepository<BlindAuction, Long> {

    @Query("""
        SELECT new cat.tecnocampus.stickeralbum.application.outputDTOs.BlindAuctionDTO(ba.owner.id, ba.owner.email, 
            ba.sticker.id, ba.sticker.name, ba.initialPrice, ba.beginDate, ba.endDate) 
        FROM BlindAuction ba 
        WHERE ba.sticker.id = :stickerId
              AND CURRENT_DATE BETWEEN ba.beginDate AND ba.endDate
        ORDER BY ba.initialPrice ASC 
        """)
    List<BlindAuctionDTO> findOpenBlindAuctionsOfSticker(@Param("stickerId") Long stickerId);




    @Query("""
        SELECT ba
        FROM BlindAuction ba
        WHERE ba.endDate = CURRENT_DATE
        """)
    List<BlindAuction> findExpiredYesterday();
}
