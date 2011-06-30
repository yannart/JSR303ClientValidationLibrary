/**
 * 
 */
package com.yannart.validation.impl;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Validator;
import javax.validation.metadata.BeanDescriptor;
import javax.validation.metadata.ConstraintDescriptor;
import javax.validation.metadata.PropertyDescriptor;

import com.yannart.validation.ConstrainedProperty;
import com.yannart.validation.JSR303ToConstrainedProperties;
import com.yannart.validation.converter.ConstraintConverterFactory;
import com.yannart.validation.converter.JSR303ConstraintConverter;
import com.yannart.validation.converter.impl.ConstraintConverterFactoryImpl;

/**
 * Convert a class annotated with JSR303 to a Set of constrained properties.
 * 
 * @author Yann Nicolas
 */
public class JSR303ToConstrainedPropertiesImpl implements JSR303ToConstrainedProperties {

	/**
	 * Factory used to obtain the converter instances.
	 */
	protected ConstraintConverterFactory converterFactory = new ConstraintConverterFactoryImpl();

	/* (non-Javadoc)
	 * @see com.yannart.validation.impl.JSR303ToConstrainedProperties#generateConstrainedProperties(java.lang.Class, javax.validation.Validator)
	 */
	public Set<ConstrainedProperty> generateConstrainedProperties(
			final Class<?> clazz, final Validator validator) {

		// Set that will contain the constraint properties of the class to
		// validate
		Set<ConstrainedProperty> propertySet = new HashSet<ConstrainedProperty>();

		BeanDescriptor beanDescriptor = validator.getConstraintsForClass(clazz);

		Set<PropertyDescriptor> constrainedProperties = beanDescriptor
				.getConstrainedProperties();

		// For each property, convert from JSR303 to JQuery validator
		// constraints.
		for (PropertyDescriptor constrainedProperty : constrainedProperties) {

			String propertyName = constrainedProperty.getPropertyName();

			ConstrainedProperty property = new ConstrainedProperty(propertyName);

			Set<ConstraintDescriptor<?>> constraintDescriptors = constrainedProperty
					.getConstraintDescriptors();

			for (ConstraintDescriptor<?> constraintDescriptor : constraintDescriptors) {

				Annotation annotation = constraintDescriptor.getAnnotation();
				Map<String, Object> attributes = constraintDescriptor
						.getAttributes();

				// Get all the converters capable to trait the annotation
				Set<JSR303ConstraintConverter> converters = converterFactory
						.getConverterMapByAnnotationClass(annotation
								.annotationType());

				// If converters are usable, use it to fill attributes of the
				// property
				if (converters != null) {
					for (JSR303ConstraintConverter converter : converters) {
						converter.fillConstrainedPropertyAttributes(annotation,
								attributes, property);
					}
				}
			}

			// A property is considered only if it has almost 1 attribute.
			if (property.getAttributeNumber() > 0) {
				propertySet.add(property);
			}
		}

		return propertySet;
	}
}
