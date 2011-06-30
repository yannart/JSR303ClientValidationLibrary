package com.yannart.validation.converter;

import java.util.Set;

/**
 * Interface of factories capable of getting the appropriate converters to
 * convert properties with annotations.
 * 
 * @author Yann Nicolas
 */
public interface ConstraintConverterFactory {

	/**
	 * Obtain a set of converters usable to convert the provided annotation.
	 * 
	 * @param annotationClass
	 *            class of the annotation for which supported converters are
	 *            searched.
	 * @return set of converters usable to convert the provided annotation.
	 */
	public abstract Set<JSR303ConstraintConverter> getConverterMapByAnnotationClass(
			final Class<?> annotationClass);

}