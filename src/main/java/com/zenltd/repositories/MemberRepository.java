package com.zenltd.repositories;

import com.zenltd.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
    @Query(value = "SELECT t FROM Member t where t.id = :id")
    Member getMemberById(Long id);
}
