package cat.tecnocampus.stickeralbum.persistence;

import cat.tecnocampus.stickeralbum.application.outputDTOs.BidDTO;
import cat.tecnocampus.stickeralbum.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {

    @Query("""
        SELECT new cat.tecnocampus.stickeralbum.application.outputDTOs.BidDTO(b.bidder.id, b.bidder.email, 
            b.auction.id, b.auction.sticker.id, b.auction.sticker.name, b.offer, b.date)
        FROM Bid b 
        WHERE b.auction.id = :auctionId 
        ORDER BY b.date ASC
    """)
    List<BidDTO> findBidsByAuctionId(Long auctionId);

    //return the last bid of the auction
    @Query("""
        SELECT new cat.tecnocampus.stickeralbum.application.outputDTOs.BidDTO(b.bidder.id, b.bidder.email, 
            b.auction.id, b.auction.sticker.id, b.auction.sticker.name, b.offer, b.date)
        FROM Bid b 
        WHERE b.auction.id = :auctionId 
        ORDER BY b.date DESC
    """)
    BidDTO findLastBidByAuctionId(Long auctionId);

    //return the amount of his last bid if he didnt bid return 0
    @Query("""
        SELECT b.offer
        FROM Bid b 
        WHERE b.auction.id = :auctionId AND b.bidder.id = :bidderId
        ORDER BY b.date DESC
    """)
    double findLastBidAmountByAuctionIdAndBidderId(Long auctionId, Long bidderId);
}


