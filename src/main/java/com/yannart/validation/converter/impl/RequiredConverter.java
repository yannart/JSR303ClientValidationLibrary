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

import java.lang.annotation.Annotation;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.yannart.validation.ConstrainedProperty;
import com.yannart.validation.converter.JSR303ConstraintConverter;

/**
 * Converter from the annotation <code>NotNull</code> to the attribute
 * "required".
 * 
 * @author Yann Nicolas
 */
public class RequiredConverter implements JSR303ConstraintConverter {

	/**
	 * {@inheritDoc}
	 */
	public Class<?>[] annotationClassConverted() {
		return new Class<?>[] { NotNull.class };
	}

	/**
	 * {@inheritDoc}
	 */
	public void fillConstrainedPropertyAttributes(final Annotation annotation,
			final Map<String, Object> attributes,
			final ConstrainedProperty validatedProperty) {

		if (annotation instanceof NotNull) {
			validatedProperty.addAttribute("required", "true");
		}
	}
}