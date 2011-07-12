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
	 * Generates a set of constrained properties from a Bean class annotated
	 * with JRE303 annotations.
	 * 
	 * @param clazz
	 *            Class of the Bean.
	 * @param validator
	 *            Validator used to find the Bean constraints.
	 * @param propertiesToIgnore
	 *            Properties to ignore from the generation.
	 * @return A set of constrained properties.
	 */
	public abstract Set<ConstrainedProperty> generateConstrainedProperties(
			final Class<?> clazz, final Validator validator,
			final String... propertiesToIgnore);

	/**
	 * Sets the Converter Factory to use when converting the data.
	 * 
	 * @param converterFactory
	 *            the converterFactory to set.
	 */
	public void setConverterFactory(final ConstraintConverterFactory converterFactory);

}