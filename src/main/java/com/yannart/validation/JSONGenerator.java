package com.yannart.validation;

import java.util.Map;
import java.util.Set;

/**
 * Generates JSON code from a set of constrained properties.
 * 
 * @author Yann Nicolas
 */
public class JSONGenerator {

	/**
	 * Generates the JSON code from a set of constrained properties.
	 * 
	 * @param constrainedProperties
	 *            set of constrained properties to include in the generated
	 *            JSON.
	 * @return String that contents the JSON code.
	 */
	public String renderJSON(
			final Set<ConstrainedProperty> constrainedProperties) {

		// Creates the builder with the starting string
		StringBuilder resultJson = new StringBuilder("rules: {\r\n");

		// Counter to indicate if the property is the first one
		int propertyNum = 0;

		// Generates the code for a property
		for (ConstrainedProperty property : constrainedProperties) {
			Map<String, String> fieldAttributeMap = property.getAttributeMap();

			if (propertyNum > 0) {
				resultJson.append(",\r\n");
			}

			resultJson.append("\t");
			resultJson.append(property.getName());
			resultJson.append(": {\r\n");

			// Counter to indicate if the constraint is the first one
			int constraintNum = 0;

			// Generates the code for a constraint attribute
			for (String attribute : fieldAttributeMap.keySet()) {
				String value = fieldAttributeMap.get(attribute);

				if (constraintNum > 0) {
					resultJson.append(",\r\n");
				}

				resultJson.append("\t\t");
				resultJson.append(attribute);
				resultJson.append(": ");
				resultJson.append(value);

				constraintNum++;
			}

			resultJson.append("\r\n\t}");
			propertyNum++;

		}

		// closes the root brackets
		resultJson.append("\r\n}");

		return resultJson.toString();

	}
}
