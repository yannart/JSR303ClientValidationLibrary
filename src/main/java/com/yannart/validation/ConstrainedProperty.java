package com.yannart.validation;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents a constrained property with a list of parameters.
 * 
 * @author Yann Nicolas
 */
public class ConstrainedProperty {
	private String name;
	private Map<String, String> attributeMap = new HashMap<String, String>();

	public ConstrainedProperty(final String name) {
		this.name = name;
	}

	public void addAttribute(final String key, final String value) {
		attributeMap.put(key, value);
	}

	/**
	 * Gets the number of attributes or the property.
	 * 
	 * @return the number of attributes or the property.
	 */
	public int getAttributeNumber() {
		return attributeMap.size();
	}

	public String getName() {
		return name;
	}

	public Map<String, String> getAttributeMap() {
		return attributeMap;
	}
}