package com.yannart.validation.converter;

import java.lang.annotation.Annotation;
import java.util.Map;

import com.yannart.validation.ConstrainedProperty;

/**
 * Interface that defines the methods to be implemented by the converters that
 * generate constraint from JSR303 annotations.
 * 
 * @author Yann Nicolas
 * 
 */
public interface JSR303ConstraintConverter {

	/**
	 * Obtain the list of JSR303 annotation classes that can be converted by
	 * this converter.
	 * 
	 * @return an array of annotation classes that can be converted by this
	 *         converter.
	 */
	Class<?>[] annotationClassConverted();

	/**
	 * Fills a ConstrainedProperty with the attributes of a JSR303 annotation.
	 * 
	 * @param annotation
	 *            Annotation that defines a validation rule for the property.
	 * @param attributes
	 *            Attributes of the annotation.
	 * @param constrainedProperty
	 *            Property to fill with constraint attributes.
	 */
	void fillConstrainedPropertyAttributes(Annotation annotation,
			Map<String, Object> attributes,
			ConstrainedProperty constrainedProperty);

}
