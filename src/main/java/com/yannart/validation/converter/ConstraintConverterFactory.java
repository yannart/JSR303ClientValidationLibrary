package com.yannart.validation.converter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Factory used to obtain the ConstraintConverters usable for a JSR303
 * constraint.
 * 
 * @author Yann Nicolas
 */
public class ConstraintConverterFactory {

	/**
	 * Default converters.
	 */
	static JSR303ConstraintConverter[] converters = new JSR303ConstraintConverter[] {
			new JSR303ConstraintConverter.MaxConverter(),
			new JSR303ConstraintConverter.MinConverter(),
			new JSR303ConstraintConverter.RequiredConverter(),
			new JSR303ConstraintConverter.SizeConverter() };

	/**
	 * Map that is used for performance purposes and that allows finding all the
	 * converters that can be used with a particular annotation.
	 */
	private Map<Class<?>, Set<JSR303ConstraintConverter>> validationConverterByAnnotationMap;

	/**
	 * Constructor.
	 */
	public ConstraintConverterFactory() {
		validationConverterByAnnotationMap = new HashMap<Class<?>, Set<JSR303ConstraintConverter>>();
		generateConverterMapByAnnotationClass();
	}

	/**
	 * Generates the map that allows finding all the converters that can be used
	 * with a particular annotation.
	 */
	protected void generateConverterMapByAnnotationClass() {

		for (JSR303ConstraintConverter converter : converters) {

			for (Class<?> convertedClass : converter.annotationClassConverted()) {

				// Gets the existing set of converters or generating an new one
				Set<JSR303ConstraintConverter> convertersForClass = validationConverterByAnnotationMap
						.get(convertedClass);
				if (convertersForClass == null) {
					convertersForClass = new HashSet<JSR303ConstraintConverter>();
				}
				convertersForClass.add(converter);

				validationConverterByAnnotationMap.put(convertedClass,
						convertersForClass);
			}
		}
	}

	/**
	 * Obtain a set of converters usable to convert the provided annotation.
	 * 
	 * @param annotationClass
	 *            class of the annotation for which supported converters are
	 *            searched.
	 * @return set of converters usable to convert the provided annotation.
	 */
	public Set<JSR303ConstraintConverter> getConverterMapByAnnotationClass(
			final Class<?> annotationClass) {
		return validationConverterByAnnotationMap.get(annotationClass);
	}
}
