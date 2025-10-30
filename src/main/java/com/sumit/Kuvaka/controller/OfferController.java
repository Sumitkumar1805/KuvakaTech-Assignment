package com.sumit.Kuvaka.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumit.Kuvaka.model.Offer;
import com.sumit.Kuvaka.service.OfferService;

@RestController
@RequestMapping("/offer")
public class OfferController {
	private final OfferService offerService;
    public OfferController(OfferService offerService) { this.offerService = offerService; }

    @PostMapping
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        Offer saved = offerService.save(offer);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOffer(@PathVariable String id) {
        Offer o = offerService.findById(id);
        if (o == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(o);
    }
    @GetMapping
    public ResponseEntity<?> getAllOffers() {
        return ResponseEntity.ok(offerService.findAll());
    }
}
