package com.healthconnect.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healthconnect.user.model.common.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT ad from Address ad WHERE ad.locality = :locality AND ad.city = :city AND ad.deleted = :deleted")
    Address findAddressByLocalityAndCity(@Param("locality") String locality, @Param("city") String city, @Param("deleted") boolean delete);
    
    @Modifying
	 @Query(value = "UPDATE Address SET Country= :country, State = :state, City = :city, District = :district, FullAddress = :fullAddress, Locality = :locality, Pin = :pin  WHERE RecordId = :id ", nativeQuery = true)
    Address updateAddress(@Param("country") String country, @Param("state") String state, @Param("city") String city, @Param("district") String district, @Param("fullAddress") String fullAddress,
    		@Param("locality") String locality, @Param("pin") String pin, @Param("id")long recordId);
}