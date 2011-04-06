/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.domain;

/**
 *
 * @author Suhail
 */
public class Address {
    
    private String streetAddress;
    private String postalCode;
    private String region;
    private String locality;
    private String country;
    
    private String latitude;
    private String longitude;
    
    private String type;
    private String formatted;
    
    public Address() {}
    
    private Address(String streetAddress, String postalCode, String region, String locality, String country) {
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.region = region;
        this.locality = locality;
        this.country = country;
    }
    
    public Address create(String streetAddress, String postalCode, String region, String locality, String country) {
        return new Address(streetAddress, postalCode, region, locality, country);
    }

    /**
     * @return the streetAddress
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * @param streetAddress the streetAddress to set
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return the locality
     */
    public String getLocality() {
        return locality;
    }

    /**
     * @param locality the locality to set
     */
    public void setLocality(String locality) {
        this.locality = locality;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the formatted
     */
    public String getFormatted() {
        return formatted;
    }

    /**
     * @param formatted the formatted to set
     */
    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }
}
