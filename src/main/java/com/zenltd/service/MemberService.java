package com.zenltd.service;

import com.zenltd.dto.AddressDto;
import com.zenltd.dto.MemberDto;
import com.zenltd.entity.Address;
import com.zenltd.entity.Member;
import com.zenltd.exception.EntityNotFoundException;
import com.zenltd.repositories.AddressRepository;
import com.zenltd.repositories.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Slf4j
@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Transactional // Ensures that both Address & Member are saved together
    public void saveMember(MemberDto memberDto) {
        log.info("Saving Member");

            Member member = new Member();
            member.setName(memberDto.getName());
            member.setGstNo(memberDto.getGstNo());
            member.setRepresentativeName(memberDto.getRepresentativeName());
            member.setPhoneNo(memberDto.getPhoneNo());
            member.setEmailId(memberDto.getEmailId());
            member.setAddressProofId(memberDto.getAddressProofId());
            member.setConstitution(memberDto.getConstitution());
            member.setPanNo(memberDto.getPanNo());

            Address address = new Address();
            address.setBuildingNo(memberDto.getAddressDto().getBuildingNo());
            address.setStreetNo(memberDto.getAddressDto().getStreetNo());
            address.setCity(memberDto.getAddressDto().getCity());
            address.setState(memberDto.getAddressDto().getState());
            address.setPinCode(memberDto.getAddressDto().getPinCode());
            addressRepository.save(address);

            member.setAddressId(address.getId());
            memberRepository.save(member);
    }
    public MemberDto getMemberById(long id) {
            log.info("Fetching Member by id {} ",id);
            Member member = memberRepository.getMemberById(id);
            // NullCheck for Member
            if(member == null){
                log.error("Member Not Found with id {}",id);
                throw new EntityNotFoundException("Member Not Found with id : " + id);
            }
            MemberDto memberDto = new MemberDto();
            memberDto.setId(member.getId());
            memberDto.setName(member.getName());
            memberDto.setRepresentativeName(member.getRepresentativeName());
            memberDto.setPhoneNo(member.getPhoneNo());
            memberDto.setEmailId(member.getEmailId());
            memberDto.setGstNo(member.getGstNo());
            memberDto.setAddressProofId(member.getAddressProofId());
            memberDto.setConstitution(member.getConstitution());
            memberDto.setPanNo(member.getPanNo());

            Address address = addressRepository.getAddressById(member.getAddressId());
          AddressDto addressDto = new AddressDto();
          addressDto.setId(address.getId());
          addressDto.setBuildingNo(address.getBuildingNo());
          addressDto.setStreetNo(address.getStreetNo());
          addressDto.setCity(address.getCity());
          addressDto.setState(address.getState());
          addressDto.setPinCode(address.getPinCode());

          memberDto.setAddressDto(addressDto);

          return memberDto;
    }
}

