package com.zenltd.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address_id")
    private long addressId;
    @Column(name = "gst_no")
    private String gstNo;
    @Column(name = "representative_name")
    private String representativeName; //authorised person's name
    @Column(name = "phone_no")
    private String phoneNo;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "address_proof_id")
    private String addressProofId;
    @Column(name = "constitution")  //this is type of business : Partnership/Trust/Company/CooperativeSociety/HUF
    private String constitution;
    @Column(name = "pan_no")
    private String panNo;
}