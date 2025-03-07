package com.zenltd.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_attribute")
public class ProductAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_type") //Electrical,Chemicals,Eatables,Stationery,Cold Drinks,FootWears,Clothing,Grocery etc.
    private String productType;
    @Column(name = "product_state") //Solid,Semi-Solid,Semi-Liquid,Liquid,Gaseous etc
    private String productState;
    @Column(name = "production_state") //RawMaterial,WorkInProgress,FinishedGoods
    private String productionState;
    @Column(name = "temperature_requirement") //Ambient,Air-conditioned,Refrigerated,Frozen,Ultra-low temperature
    private String temperatureRequirement;
    @Column(name = "shelf_life")
    private int shelfLife;
    @Column(name = "breakability") //Fragile, Frangible, Vulnerable, Delicate, Frail, and Splintery.
    private String breakability;
    @Column(name = "flammability") //noncombustible,Difficult to ignite,Normal combustibility,Easily ignited
    private String flammability;
    @Column(name = "explosibility")
    private String explosibility;
    @Column(name = "perishability") //Expirability
    private String perishability;
    @Column(name = "packaging_type") //Carton/Box/paperWrap/Bottle/
    private String packagingType;
    @Column(name = "material")
    private String material;
    @Column(name = "no_of_units")
    private int noOfUnits;
    @Column(name = "manufacturer_details")
    private String manufacturerDetails;
    @Column(name = "country_of_origin")
    private String countryOfOrigin;
    @Column(name = "marketed_by")
    private String marketedBy;
    @Column(name = "seller")
    private String seller;
}
