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