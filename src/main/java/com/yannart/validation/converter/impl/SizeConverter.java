package com.yannart.validation.converter.impl;

import java.lang.annotation.Annotation;
import java.util.Map;

import javax.validation.constraints.Size;

import com.yannart.validation.ConstrainedProperty;
import com.yannart.validation.converter.JSR303ConstraintConverter;

/**
 * Converter from the annotation <code>Size</code> to the attributes "minlength"
 * and "maxlength".
 * 
 * @author Yann Nicolas
 */
public class SizeConverter implements JSR303ConstraintConverter {

	/**
	 * {@inheritDoc}
	 */
	public Class<?>[] annotationClassConverted() {
		return new Class<?>[] { Size.class };
	}

	/**
	 * {@inheritDoc}
	 */
	public void fillConstrainedPropertyAttributes(final Annotation annotation,
			final Map<String, Object> attributes,
			final ConstrainedProperty validatedProperty) {

		if (annotation instanceof Size) {
			if (attributes.containsKey("min")) {
				validatedProperty.addAttribute("minlength",
						attributes.get("min").toString());
			}

			if (attributes.containsKey("max")) {
				validatedProperty.addAttribute("maxlength",
						attributes.get("max").toString());
			}
		}
	}
}