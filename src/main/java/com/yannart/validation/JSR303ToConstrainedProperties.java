package com.yannart.validation;

import java.util.Set;

import javax.validation.Validator;

import com.yannart.validation.converter.ConstraintConverterFactory;

/**
 * Generates constrained properties from a Bean class annotated with JRE303
 * annotations.
 * 
 * @author Yann Nicolas
 */
public interface JSR303ToConstrainedProperties {

	/**
	 * Generates a set of constrained properties from a Bean class annotated
	 * with JRE303 annotations.
	 * 
	 * @param clazz
	 *            Class of the Bean.
	 * @param validator
	 *            Validator used to find the Bean constraints.
	 * @return A set of constrained properties.
	 */
	public abstract Set<ConstrainedProperty> generateConstrainedProperties(
			final Class<?> clazz, final Validator validator);
	
	/**
	 * Sets the Converter Factory to use when converting the data.
	 * 
	 * @param converterFactory
	 *            the converterFactory to set.
	 */
	public void setConverterFactory(
			ConstraintConverterFactory converterFactory);

}