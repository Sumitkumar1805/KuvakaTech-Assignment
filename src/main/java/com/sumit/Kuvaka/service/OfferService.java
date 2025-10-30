package com.sumit.Kuvaka.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.sumit.Kuvaka.model.Offer;
import com.sumit.Kuvaka.repository.OfferRepository;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public Offer save(Offer offer) {
        return offerRepository.save(offer);
    }

    public Offer findById(String id) {
        return offerRepository.findById(id).orElse(null);
    }

    public List<Offer> findAll() {
        return offerRepository.findAll();
    }
}