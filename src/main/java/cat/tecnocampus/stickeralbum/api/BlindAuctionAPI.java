package cat.tecnocampus.stickeralbum.api;

import cat.tecnocampus.stickeralbum.application.BlindAuctionService;
import cat.tecnocampus.stickeralbum.application.inputDTOs.BidCommand;
import cat.tecnocampus.stickeralbum.application.inputDTOs.BlindAuctionCommand;
import cat.tecnocampus.stickeralbum.application.outputDTOs.BidDTO;
import cat.tecnocampus.stickeralbum.application.outputDTOs.BlindAuctionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlindAuctionAPI {
    private final BlindAuctionService blindAuctionService;
    public BlindAuctionAPI(BlindAuctionService blindAuctionService) {
        this.blindAuctionService = blindAuctionService;
    }

    @PostMapping("/collectors/blindAuctions")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBlindAuction(@RequestBody BlindAuctionCommand blindAuctionCommand) {
        blindAuctionService.createBlindAuction(blindAuctionCommand);
    }

    @GetMapping("/blindAuctions/stickers/{stickerId}")
    public List<BlindAuctionDTO> getBlindAuctionsOfSticker(@PathVariable Long stickerId) {
        return blindAuctionService.getOpenBlindAuctionsOfSticker(stickerId);
    }

    @PostMapping("/blindAuctions/bid")
    @ResponseStatus(HttpStatus.CREATED)
    public void bidBlindly(@RequestBody BidCommand bidCommand) throws Exception {
        blindAuctionService.bidBlindly(bidCommand);
    }

    @GetMapping("/blindAuctions/{auctionId}/bids")
    public List<BidDTO> getBidsOfAuction(@PathVariable Long auctionId) {
        return blindAuctionService.getBidsOfAuction(auctionId);
    }

    @PostMapping("/blindAuction/complete")
    @ResponseStatus(HttpStatus.OK)
    public void completeBid() {
        blindAuctionService.completeAuction();
    }
}
