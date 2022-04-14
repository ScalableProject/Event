package com.event.scp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.scp.entity.Booking;

//interface repo for accessing booking through PK ID

@Repository
public interface BookingRepository extends JpaRepository <Booking,Long>{

}
