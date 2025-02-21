package com.zenltd.dto;

import javax.persistence.Entity;

public class ProductDto {
    private long id;
    private long productCode;
    private long containerId;
    private long shipmentId;
    private String productName;
    private String productType;
    private String productState;
    private String productionState;
    private String temperatureRequirement;
    private int shelfLife;
    private String breakability;
    private String flammability;
    private String explosibility;
    private String perishability;
    private String packagingType;
    private String material;
    private int noOfUnits;
    private String manufacturerDetails;
    private String countryOfOrigin;
    private String marketedBy;
    private String seller;
    //****************************************************************

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getContainerId() {
        return containerId;
    }

    public void setContainerId(long containerId) {
        this.containerId = containerId;
    }

    public long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public long getProductCode() {
        return productCode;
    }

    public void setProductCode(long productCode) {
        this.productCode = productCode;
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
