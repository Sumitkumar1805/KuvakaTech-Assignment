package com.sumit.Kuvaka.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.sumit.Kuvaka.model.Offer;

public interface OfferRepository extends JpaRepository<Offer, String> {
}
