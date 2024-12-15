package cat.tecnocampus.stickeralbum.application;

import cat.tecnocampus.stickeralbum.application.exceptions.*;
import cat.tecnocampus.stickeralbum.application.inputDTOs.BidCommand;
import cat.tecnocampus.stickeralbum.application.inputDTOs.BlindAuctionCommand;
import cat.tecnocampus.stickeralbum.application.outputDTOs.BidDTO;
import cat.tecnocampus.stickeralbum.application.outputDTOs.BlindAuctionDTO;
import cat.tecnocampus.stickeralbum.domain.*;
import cat.tecnocampus.stickeralbum.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlindAuctionService {
    private final CollectorRepository collectorRepository;
    private final HasStickerRepository hasStickerRepository;
    private final StickerRepository stickerRepository;
    private final BlindAuctionRepository blindAuctionRepository;
    private final BidRepository bidRepository;
    private final CollectionRepository collectionRepository;

    public BlindAuctionService(CollectorRepository collectorRepository, HasStickerRepository hasStickerRepository,
                               StickerRepository stickerRepository, BlindAuctionRepository blindAuctionRepository,
                               BidRepository bidRepository, CollectionRepository collectionRepository) {
        this.collectorRepository = collectorRepository;
        this.hasStickerRepository = hasStickerRepository;
        this.stickerRepository = stickerRepository;
        this.blindAuctionRepository = blindAuctionRepository;
        this.bidRepository = bidRepository;
        this.collectionRepository = collectionRepository;
    }

    @Transactional
    public void createBlindAuction(BlindAuctionCommand blindAuctionCommand) {
        Collector owner = collectorRepository.findById(blindAuctionCommand.ownerId())
                .orElseThrow(() -> new CollectorDoesNotExistException(blindAuctionCommand.ownerId()));
        Sticker sticker = stickerRepository.findById(blindAuctionCommand.stickerId())
                .orElseThrow(() -> new StickerDoesNotExistException(blindAuctionCommand.stickerId()));
        var collection = collectionRepository.findByCollectorAndSticker(owner, sticker)
                .orElseThrow(() -> new CollectionWithStickerDoesNotExists(owner.getId(), sticker.getId()));

        collection.blockSticker(sticker);
        BlindAuction blindAuction = new BlindAuction(owner, sticker, blindAuctionCommand.initialPrice(), blindAuctionCommand.beginDate(), blindAuctionCommand.endDate());
        blindAuctionRepository.save(blindAuction);
    }

    public void bidBlindly(BidCommand bidCommand) throws Exception {
        Collector bidder = collectorRepository.findById(bidCommand.bidderId())
                .orElseThrow(() -> new CollectorDoesNotExistException(bidCommand.bidderId()));
        BlindAuction blindAuction = blindAuctionRepository.findById(bidCommand.auctionId())
                .orElseThrow(() -> new BlindAuctionDoesNotExistException(bidCommand.auctionId()));
        if(!blindAuction.isOpen()) {
            throw new IllegalStateException("Auction with Id " + bidCommand.auctionId() + " is closed");
        }
        //Comprobar que tenim diners
        if (bidder.getBalance() < bidCommand.amount()) throw new CollectorDoesNotHaveEnoughBalanceException(bidCommand.bidderId());
        if (bidCommand.amount() < blindAuction.getInitialPrice()) {
            throw new IllegalStateException("Bid offer quantity " + bidCommand.amount() + " is too low");
        }
        //comprobar que no hagi fet una oferta més baixa que el ultim bid
        if (bidRepository.findLastBidByAuctionId(bidCommand.auctionId()).amount() >= bidCommand.amount()) {
            throw new IllegalStateException("Bid offer quantity " + bidCommand.amount() + " is too low");
        }
        //retornar els diners de la seva ultima bid si no en te retornar 0
        double amountlastBid = bidRepository.findLastBidAmountByAuctionIdAndBidderId(bidCommand.bidderId(), bidCommand.auctionId());

        bidder.setBalance(bidder.getBalance() + amountlastBid - bidCommand.amount());
        collectorRepository.save(bidder);

        var bid = new Bid(blindAuction, bidder, bidCommand.amount());
        bidRepository.save(bid);

    }

    public List<BlindAuctionDTO> getOpenBlindAuctionsOfSticker(Long stickerId) {
        return blindAuctionRepository.findOpenBlindAuctionsOfSticker(stickerId);
    }

    public List<BidDTO> getBidsOfAuction(Long auctionId) {
        return bidRepository.findBidsByAuctionId(auctionId);
    }


    @Transactional
    public void completeAuction() {
        List<BlindAuction> auctionsToComplete = blindAuctionRepository.findExpiredYesterday();
        for (BlindAuction auction : auctionsToComplete) {
            blindAuctionRepository.save(auction);
            BidDTO lastBid = bidRepository.findLastBidByAuctionId(auction.getId());
            if (lastBid != null) {
                Collector winner = collectorRepository.findById(lastBid.bidderId())
                        .orElseThrow(() -> new CollectorDoesNotExistException(lastBid.bidderId()));
                Collection collection = collectionRepository.findByCollectorAndSticker(winner, auction.getSticker())
                        .orElseThrow(() -> new CollectionWithStickerDoesNotExists(winner.getId(),auction.getSticker().getId()));

                HasSticker hasSticker = hasStickerRepository.findByOwnerAndSticker(winner, auction.getSticker())
                        .orElseGet(() -> new HasSticker(auction.getSticker(), collection, 1));
                hasSticker.addCopies(1);
                hasStickerRepository.save(hasSticker);
            }
            //controlar que retornem els diners als perdedors
            List<BidDTO> bids = bidRepository.findBidsByAuctionId(auction.getId());
            for (BidDTO bid : bids) {
                if (!bid.equals(lastBid)) {
                    Collector bidder = collectorRepository.findById(bid.bidderId())
                            .orElseThrow(() -> new CollectorDoesNotExistException(bid.bidderId()));
                    double lastBidAmount = bidRepository.findLastBidAmountByAuctionIdAndBidderId(bid.bidderId(), auction.getId());
                    bidder.setBalance(bidder.getBalance() + lastBidAmount);
                    collectorRepository.save(bidder);
                }
            }

        }
    }
}
