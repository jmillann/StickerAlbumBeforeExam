package cat.tecnocampus.stickeralbum.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double offer;
    @ManyToOne
    @JoinColumn(name = "bidder_id")
    private Collector bidder;
    @ManyToOne
    @JoinColumn(name = "auction_id")
    private BlindAuction auction;
    private LocalDate date;

    public Bid() {
    }

    public Bid(BlindAuction blindAuction, Collector bidder, Double amount) {
        this.auction = blindAuction;
        this.bidder = bidder;
        this.offer = amount;
        date = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getOffer() {
        return offer;
    }

    public void setOffer(Double offer) {
        this.offer = offer;
    }

    public Collector getBidder() {
        return bidder;
    }

    public void setBidder(Collector bidder) {
        this.bidder = bidder;
    }

    public BlindAuction getAuction() {
        return auction;
    }

    public void setAuction(BlindAuction auction) {
        this.auction = auction;
    }

    public LocalDate getDate() {
        return date;
    }


}
