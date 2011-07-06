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
package com.yannart.validation.json;

import java.util.Map;
import java.util.Set;

import com.yannart.validation.ConstrainedProperty;
import com.yannart.validation.ConstraintDescriptorGenerator;

/**
 * Generates JSON code from a set of constrained properties.
 * 
 * @author Yann Nicolas
 */
public class JSONDescriptorGenerator implements ConstraintDescriptorGenerator {

	private static String EMPTY = "";
	private static String SPACE = " ";
	private static String NEW_LINE = "\r\n";
	private static String TAB = "\t";

	/**
	 * Indicates if the output JSON code must be formated or not.
	 */
	private boolean format = true;

	/**
	 * Constructor of the generator that allows to indicate if the output must
	 * be formated.
	 * 
	 * @param format
	 *            Indicates if the output JSON code must be formated or not.
	 */
	public JSONDescriptorGenerator(final boolean format) {
		this.format = format;
	}

	/**
	 * Generates the JSON code from a set of constrained properties.
	 * 
	 * @param constrainedProperties
	 *            set of constrained properties to include in the generated
	 *            JSON.
	 * @return String that contents the JSON code.
	 */
	public String render(final Set<ConstrainedProperty> constrainedProperties) {
		return render(constrainedProperties, format);
	}

	/**
	 * Generates the JSON code from a set of constrained properties.
	 * 
	 * @param constrainedProperties
	 *            set of constrained properties to include in the generated
	 *            JSON.
	 * @param format
	 *            indicates if the output must be formated.
	 * @return String that contents the JSON code.
	 */
	public String render(final Set<ConstrainedProperty> constrainedProperties,
			final boolean format) {

		// Define the blank characters that will be used to format the output.
		// If the output is not formated, empty characters are used.
		String space = EMPTY;
		String newLine = EMPTY;
		String tab = EMPTY;
		if (format == true) {
			space = SPACE;
			newLine = NEW_LINE;
			tab = TAB;
		}

		// Creates the builder with the starting string
		StringBuilder resultJson = new StringBuilder("rules:");
		resultJson.append(space);
		resultJson.append("{");
		resultJson.append(newLine);

		// Counter to indicate if the property is the first one
		int propertyNum = 0;

		// Generates the code for a property
		for (ConstrainedProperty property : constrainedProperties) {
			Map<String, String> fieldAttributeMap = property.getAttributeMap();

			if (propertyNum > 0) {
				resultJson.append(",");
				resultJson.append(newLine);
			}

			resultJson.append(tab);
			resultJson.append(property.getName());
			resultJson.append(":");
			resultJson.append(space);
			resultJson.append("{");
			resultJson.append(newLine);

			// Counter to indicate if the constraint is the first one
			int constraintNum = 0;

			// Generates the code for a constraint attribute
			for (String attribute : fieldAttributeMap.keySet()) {
				String value = fieldAttributeMap.get(attribute);

				if (constraintNum > 0) {
					resultJson.append(",");
					resultJson.append(newLine);
				}

				resultJson.append(tab);
				resultJson.append(tab);
				resultJson.append(attribute);
				resultJson.append(":");
				resultJson.append(space);
				resultJson.append(value);

				constraintNum++;
			}

			resultJson.append(newLine);
			resultJson.append(tab);
			resultJson.append("}");
			propertyNum++;
		}

		// closes the root brackets
		resultJson.append(newLine);
		resultJson.append("}");

		return resultJson.toString();
	}

	/**
	 * Indicates if the output is formatted or not.
	 * 
	 * @return true is the output is formatted, false if not.
	 */
	public final boolean isFormat() {
		return format;
	}
}
