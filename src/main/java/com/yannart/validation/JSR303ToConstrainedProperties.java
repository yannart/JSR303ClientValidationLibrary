/**
 * 
 */
package com.yannart.validation;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Validator;
import javax.validation.metadata.BeanDescriptor;
import javax.validation.metadata.ConstraintDescriptor;
import javax.validation.metadata.PropertyDescriptor;

import com.yannart.validation.converter.ConstraintConverterFactory;
import com.yannart.validation.converter.JSR303ConstraintConverter;

/**
 * Convert a class annotated with JSR303 to a Set of contrained properties.
 * 
 * @author Yann Nicolas
 */
public class JSR303ToConstrainedProperties {

	/**
	 * Factory used to obtain the converter instances.
	 */
	protected ConstraintConverterFactory converterFactory = new ConstraintConverterFactory();

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
	public Set<ConstrainedProperty> generateConstrainedProperties(
			final Class<?> clazz, final Validator validator) {

		Set<ConstrainedProperty> propertySet = new HashSet<ConstrainedProperty>();

		BeanDescriptor beanDescriptor = validator.getConstraintsForClass(clazz);

		Set<PropertyDescriptor> constrainedProperties = beanDescriptor
				.getConstrainedProperties();

		for (PropertyDescriptor constrainedProperty : constrainedProperties) {

			String propertyName = constrainedProperty.getPropertyName();

			ConstrainedProperty property = new ConstrainedProperty(propertyName);

			Set<ConstraintDescriptor<?>> constraintDescriptors = constrainedProperty
					.getConstraintDescriptors();

			for (ConstraintDescriptor<?> constraintDescriptor : constraintDescriptors) {
				Annotation annotation = constraintDescriptor.getAnnotation();
				Map<String, Object> attributes = constraintDescriptor
						.getAttributes();

				Set<JSR303ConstraintConverter> converters = converterFactory
						.getConverterMapByAnnotationClass(annotation
								.annotationType());

				if (converters != null) {
					for (JSR303ConstraintConverter converter : converters) {
						converter.fillConstrainedPropertyAttributes(annotation,
								attributes, property);
					}
				}
			}

			if (property.getAttributeNumber() > 0) {
				propertySet.add(property);
			}
		}

		return propertySet;
	}
}
