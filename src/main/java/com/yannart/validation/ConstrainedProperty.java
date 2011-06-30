package com.yannart.validation;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents a property with a list of constraint attributes. The
 * possible attributes follow the supported by the client validation library
 * "JQuery Validation"
 * http://bassistance.de/jquery-plugins/jquery-plugin-validation/
 * 
 * @author Yann Nicolas
 */
public class ConstrainedProperty {

	/**
	 * Property name.
	 */
	private String name;

	/**
	 * Map that constraints the attributes of the property. The key is the
	 * attribute name, the value is the attribute value.
	 */
	private Map<String, String> attributeMap = new HashMap<String, String>();

	/**
	 * Constructor of the Property with an immutable name.
	 * 
	 * @param name
	 *            name of the property.
	 */
	public ConstrainedProperty(final String name) {
		this.name = name;
	}

	/**
	 * Adds an attribute to the property.
	 * 
	 * @param name
	 *            name of the attributes.
	 * @param value
	 *            value of the attributes.
	 */
	public void addAttribute(final String name, final String value) {
		attributeMap.put(name, value);
	}

	/**
	 * Gets the number of attributes or the property.
	 * 
	 * @return the number of attributes or the property.
	 */
	public int getAttributeNumber() {
		return attributeMap.size();
	}

	/**
	 * Obtains the property name.
	 * 
	 * @return the property name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Obtains the property attributes map.
	 * 
	 * @return the property attributes map.
	 */
	public Map<String, String> getAttributeMap() {
		return attributeMap;
	}
}