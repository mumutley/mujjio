/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.domain;

/**
 *
 * @author Suhail
 */
public class Nicotine {
    
    private String key;
    private String displayName;
    
    public Nicotine() {}
    
    public Nicotine(String key, String displayName) {
        this.key = key;
        this.displayName = displayName;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }    
}
