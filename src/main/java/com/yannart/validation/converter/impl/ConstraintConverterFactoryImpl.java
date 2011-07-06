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
package com.yannart.validation.converter.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.yannart.validation.converter.ConstraintConverterFactory;
import com.yannart.validation.converter.JSR303ConstraintConverter;

/**
 * Factory used to obtain the ConstraintConverters usable for a JSR303
 * constraint.
 * 
 * @author Yann Nicolas
 */
public class ConstraintConverterFactoryImpl implements ConstraintConverterFactory {

	/**
	 * Default converters.
	 */
	static JSR303ConstraintConverter[] converters = new JSR303ConstraintConverter[] {
			new MaxConverter(),
			new MinConverter(),
			new RequiredConverter(),
			new SizeConverter() };

	/**
	 * Map that is used for performance purposes and that allows finding all the
	 * converters that can be used with a particular annotation.
	 */
	private Map<Class<?>, Set<JSR303ConstraintConverter>> validationConverterByAnnotationMap;

	/**
	 * Constructor.
	 */
	public ConstraintConverterFactoryImpl() {
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

	/* (non-Javadoc)
	 * @see com.yannart.validation.converter.ConstraintConverterFactory#getConverterMapByAnnotationClass(java.lang.Class)
	 */
	public Set<JSR303ConstraintConverter> getConverterMapByAnnotationClass(
			final Class<?> annotationClass) {
		return validationConverterByAnnotationMap.get(annotationClass);
	}
}
