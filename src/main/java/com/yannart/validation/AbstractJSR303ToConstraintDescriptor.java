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

import javax.validation.Validation;
import javax.validation.Validator;

import com.yannart.validation.converter.ConstraintConverterFactory;
import com.yannart.validation.converter.impl.ConstraintConverterFactoryImpl;
import com.yannart.validation.impl.JSR303ToConstrainedPropertiesImpl;

/**
 * Abstract method that contains the logic to render a constraint descriptor
 * from an annotated Bean.
 * 
 * @author Yann Nicolas
 * 
 */
public class AbstractJSR303ToConstraintDescriptor implements
		JSR303ToConstraintDescriptor {

	/**
	 * Obtains a set of constrained properties from JSR303 annotations.
	 */
	JSR303ToConstrainedProperties jsr303ToConstrainedProperties = new JSR303ToConstrainedPropertiesImpl();

	/**
	 * Constraint converter factory.
	 */
	ConstraintConverterFactory constraintConverterFactory = new ConstraintConverterFactoryImpl();

	/**
	 * Generator of constraint descriptor.
	 */
	ConstraintDescriptorGenerator constraintDescriptorGenerator;

	/**
	 * Validator used to analyse JSR303 annotations.
	 */
	Validator validator = Validation.buildDefaultValidatorFactory()
			.getValidator();

	/**
	 * Constructor where a constraint descriptor generator must be defined.
	 * 
	 * @param constraintDescriptorGenerator
	 *            the descriptor generator to use.
	 */
	public AbstractJSR303ToConstraintDescriptor(
			final ConstraintDescriptorGenerator constraintDescriptorGenerator) {
		this.constraintDescriptorGenerator = constraintDescriptorGenerator;

		jsr303ToConstrainedProperties
				.setConverterFactory(constraintConverterFactory);
	}

	/**
	 * {@inheritDoc}
	 */
	public String render(final Class<?> clazz) {

		// Call the render method with no ignored properties.
		return render(clazz, new String[] {});
	}

	/**
	 * {@inheritDoc}
	 */
	public String render(final Class<?> clazz,
			final String... propertiesToIgnore) {

		Set<ConstrainedProperty> constrainedProperties = jsr303ToConstrainedProperties
				.generateConstrainedProperties(clazz, validator,
						propertiesToIgnore);

		return constraintDescriptorGenerator.render(constrainedProperties);
	}

	/**
	 * @return the constraintConverterFactory
	 */
	public final ConstraintConverterFactory getConstraintConverterFactory() {
		return constraintConverterFactory;
	}

	/**
	 * @param constraintConverterFactory
	 *            the constraintConverterFactory to set
	 */
	public final void setConstraintConverterFactory(
			ConstraintConverterFactory constraintConverterFactory) {
		this.constraintConverterFactory = constraintConverterFactory;
	}

	/**
	 * @return the jsr303ToConstrainedProperties
	 */
	public final JSR303ToConstrainedProperties getJsr303ToConstrainedProperties() {
		return jsr303ToConstrainedProperties;
	}

	/**
	 * @return the constraintDescriptorGenerator
	 */
	public final ConstraintDescriptorGenerator getConstraintDescriptorGenerator() {
		return constraintDescriptorGenerator;
	}

	/**
	 * @param jsr303ToConstrainedProperties
	 *            the jsr303ToConstrainedProperties to set
	 */
	public final void setJsr303ToConstrainedProperties(
			final JSR303ToConstrainedProperties jsr303ToConstrainedProperties) {
		this.jsr303ToConstrainedProperties = jsr303ToConstrainedProperties;
	}

	/**
	 * @param constraintDescriptorGenerator
	 *            the constraintDescriptorGenerator to set
	 */
	public final void setConstraintDescriptorGenerator(
			final ConstraintDescriptorGenerator constraintDescriptorGenerator) {
		this.constraintDescriptorGenerator = constraintDescriptorGenerator;
	}

	/**
	 * @param validator
	 *            the validator to set
	 */
	public final void setValidator(final Validator validator) {
		this.validator = validator;
	}
}
