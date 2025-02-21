package com.zenltd.entity;


import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "product_code")
    private long productCode;
    @Column(name = "container_id")
    private long containerId;
    @Column(name = "shipment_id")
    private long shipmentId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_type") //Electricals,Chemicals,Eatables,Stationery,Cold Drinks,FootWears,Clothing,Grocery etc.
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
    //**************************************************************************


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductCode() {
        return productCode;
    }

    public void setProductCode(long productCode) {
        this.productCode = productCode;
    }

    public long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public long getContainerId() {
        return containerId;
    }

    public void setContainerId(long containerId) {
        this.containerId = containerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }

    public String getProductionState() {
        return productionState;
    }

    public void setProductionState(String productionState) {
        this.productionState = productionState;
    }

    public String getTemperatureRequirement() {
        return temperatureRequirement;
    }

    public void setTemperatureRequirement(String temperatureRequirement) {
        this.temperatureRequirement = temperatureRequirement;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(int shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getBreakability() {
        return breakability;
    }

    public void setBreakability(String breakability) {
        this.breakability = breakability;
    }

    public String getFlammability() {
        return flammability;
    }

    public void setFlammability(String flammability) {
        this.flammability = flammability;
    }

    public String getExplosibility() {
        return explosibility;
    }

    public void setExplosibility(String explosibility) {
        this.explosibility = explosibility;
    }

    public String getPerishability() {
        return perishability;
    }

    public void setPerishability(String perishability) {
        this.perishability = perishability;
    }

    public String getPackagingType() {
        return packagingType;
    }

    public void setPackagingType(String packagingType) {
        this.packagingType = packagingType;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getNoOfUnits() {
        return noOfUnits;
    }

    public void setNoOfUnits(int noOfUnits) {
        this.noOfUnits = noOfUnits;
    }

    public String getManufacturerDetails() {
        return manufacturerDetails;
    }

    public void setManufacturerDetails(String manufacturerDetails) {
        this.manufacturerDetails = manufacturerDetails;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getMarketedBy() {
        return marketedBy;
    }

    public void setMarketedBy(String marketedBy) {
        this.marketedBy = marketedBy;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
