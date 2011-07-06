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
package com.yannart.validation;

import java.util.Set;

/**
 * Interfaces used by generators of constraint descriptors from the properties
 * of a Bean.
 * 
 * @author Yann Nicolas
 */
public interface ConstraintDescriptorGenerator {

	/**
	 * Generates a constraint descriptor from a set of constrained properties.
	 * 
	 * @param constrainedProperties
	 *            set of constrained properties to include in the generated
	 *            descriptor.
	 * @return String that contents the generated code.
	 */
	public abstract String render(
			final Set<ConstrainedProperty> constrainedProperties);

}