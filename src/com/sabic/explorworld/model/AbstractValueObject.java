package com.sabic.explorworld.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AbstractValueObject {
	
	public AbstractValueObject() {
		
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
