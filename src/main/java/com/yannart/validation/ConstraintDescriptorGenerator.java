package com.yannart.validation;

import java.util.Set;

/**
 * Interfaces used by generators of constraint descriptors from the properties
 * of a Bean.
 * 
 * @author Yann Nicolas
 * 
 */
public interface ConstraintDescriptorGenerator {

	/**
	 * Generates a constraint descriptor from a set of constrained properties.
	 * 
	 * @param constrainedProperties
	 *            set of constrained properties to include in the generated
	 *            descriptor.
	 * @return String that contents the generated code.
	 */
	public abstract String render(
			final Set<ConstrainedProperty> constrainedProperties);

}