package me.moimoi.social.herql.domain;

import org.mutuality.herql.aspects.ManagedComponent;

@ManagedComponent
public class SimpleClass {
	private int primitiveInt;
	private Integer referenceInt;
	private String referenceString;
	
	public SimpleClass(){}
	
	public int getPrimitiveInt() {
		return primitiveInt;
	}
	
	public void setPrimitiveInt(int primitiveInt) {
		this.primitiveInt = primitiveInt;
	}
	
	public Integer getReferenceInt() {
		return referenceInt;
	}
	
	public void setReferenceInt(Integer referenceInt) {
		this.referenceInt = referenceInt;
	}
	
	public String getReferenceString() {
		return referenceString;
	}
	
	public void setReferenceString(String referenceString) {
		this.referenceString = referenceString;
	}		
}
