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

/**
 * Generates a constraint descriptor from a Bean annotated with JSR303
 * annotations.
 * 
 * @author Yann Nicolas
 */
public interface JSR303ToConstraintDescriptor {

	/**
	 * Generates a constraint descriptor from a class annotated with JSR303
	 * annotations.
	 * 
	 * @param class class annotated with JSR303 annotations for which a
	 *        descriptor is generated.
	 * @return String that contents the generated code of the constraint
	 *         descriptor.
	 */
	public abstract String render(final Class<?> clazz);

	/**
	 * Generates a constraint descriptor from a class annotated with JSR303
	 * annotations.
	 * 
	 * @param class class annotated with JSR303 annotations to which a
	 *        descriptor is generated.
	 * @param propertiesToIgnore
	 *            properties to ignore in the descriptor.
	 * @return String that contents the generated code of the constraint
	 *         descriptor.
	 */
	public abstract String render(final Class<?> clazz,
			String... propertiesToIgnore);
}
