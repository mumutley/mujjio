/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.moimoi.social.herql.domain;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.PostLoad;
import com.google.code.morphia.annotations.PreLoad;
import com.mongodb.BasicDBObject;
import java.util.Iterator;
import org.apache.shindig.protocol.model.Enum;

/**
 *
 * @author ManzoorS
 */
@Embedded
public final class EnumType<E extends Enum.EnumKey> implements Enum<E> {

    private String displayValue;
    private E value = null;

    /**
     * 
     */
    public EnumType() {
        
    }
    /**
     * Constructs a Enum object.
     * 
     * @param value EnumKey The key to use
     * @param displayValue String The display value
     */
    public EnumType(E value, String displayValue) {
        this.value = value;
        this.displayValue = displayValue;
    }

    /**
     * Constructs a Enum object.
     * 
     * @param value The key to use. Will use the value from getDisplayValue() as the display value.
     */
    public EnumType(E value) {
        this(value, value.getDisplayValue());
    }

    @Override
    public String getDisplayValue() {
        return displayValue;
    }

    @Override
    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    @Override
    public E getValue() {
        return value;
    }

    @Override
    public void setValue(E value) {
        this.value = value;
    }
    
    @PreLoad void preLoad(BasicDBObject dbObj) {
        Iterator ita =  dbObj.keySet().iterator();
        while(ita.hasNext()) {
            System.out.println(ita.next());
        }
        System.out.println(" --->> " + this.getValue());
    }
    
    @PostLoad void postLoad(BasicDBObject dbObj) {
        System.out.println(" --->> " + dbObj.getClass().getName());
    }
}
