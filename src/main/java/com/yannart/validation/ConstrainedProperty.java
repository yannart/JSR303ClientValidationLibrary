/**
 * Copyright (C) 2011 Yann Nicolas <yannart@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yannart.validation;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class that represents a property with a list of constraint attributes. The
 * possible attributes follow the supported by the client validation library
 * "JQuery Validation"
 * http://bassistance.de/jquery-plugins/jquery-plugin-validation/
 * 
 * @author Yann Nicolas
 */
public class ConstrainedProperty implements Comparable<ConstrainedProperty>{

	/**
	 * Property name.
	 */
	private String name;

	/**
	 * Map that constraints the attributes of the property. The key is the
	 * attribute name, the value is the attribute value.
	 */
	private Map<String, String> attributeMap = new LinkedHashMap <String, String>();

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

	public int compareTo(final ConstrainedProperty other) {
		
		return name.compareTo(other.name);
	}
}