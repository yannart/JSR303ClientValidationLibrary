package com.yannart.validation.converter;

import java.lang.annotation.Annotation;
import java.util.Map;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	/**
	 * Converter from the annotation <code>Size</code> to the attributes
	 * "minlength" and "maxlength".
	 */
	static class SizeConverter implements JSR303ConstraintConverter {

		public Class<?>[] annotationClassConverted() {
			return new Class<?>[] { Size.class };
		}

		public void fillConstrainedPropertyAttributes(Annotation annotation,
				Map<String, Object> attributes,
				ConstrainedProperty validatedProperty) {

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

	/**
	 * Converter from the annotation <code>Min</code> to the attribute "min".
	 */
	static class MinConverter implements JSR303ConstraintConverter {

		public Class<?>[] annotationClassConverted() {
			return new Class<?>[] { Min.class };
		}

		public void fillConstrainedPropertyAttributes(Annotation annotation,
				Map<String, Object> attributes,
				ConstrainedProperty validatedProperty) {

			if (annotation instanceof Min) {
				if (attributes.containsKey("min")) {
					validatedProperty.addAttribute("min", attributes.get("min")
							.toString());
				}
			}
		}
	}

	/**
	 * Converter from the annotation <code>Max</code> to the attribute "max".
	 */
	static class MaxConverter implements JSR303ConstraintConverter {

		public Class<?>[] annotationClassConverted() {
			return new Class<?>[] { Max.class };
		}

		public void fillConstrainedPropertyAttributes(Annotation annotation,
				Map<String, Object> attributes,
				ConstrainedProperty validatedProperty) {

			if (annotation instanceof Max) {
				if (attributes.containsKey("max")) {
					validatedProperty.addAttribute("max", attributes.get("max")
							.toString());
				}
			}
		}
	}

	/**
	 * Converter from the annotation <code>NotNull</code> to the attribute "required".
	 */
	static class RequiredConverter implements JSR303ConstraintConverter {

		public Class<?>[] annotationClassConverted() {
			return new Class<?>[] { NotNull.class };
		}

		public void fillConstrainedPropertyAttributes(Annotation annotation,
				Map<String, Object> attributes,
				ConstrainedProperty validatedProperty) {

			if (annotation instanceof NotNull) {
				validatedProperty.addAttribute("required", "true");
			}
		}
	}

}
