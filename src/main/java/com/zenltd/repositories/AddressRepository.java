package com.zenltd.repositories;

import com.zenltd.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
    public interface AddressRepository extends JpaRepository<Address, Long> {

        @Query(value = "SELECT t FROM Address t where t.id= :id")
        Address getAddressById(Long id);
    }

