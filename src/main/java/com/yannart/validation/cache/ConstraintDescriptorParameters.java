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
package com.yannart.validation.cache;

import java.util.Set;

/**
 * Class that contains the inputs that generate an unique constraint descriptor.
 * 
 * @author Yann Nicolas
 */
public class ConstraintDescriptorParameters {

	/**
	 * Class to which the constraint descriptor is generated.
	 */
	Class<?> clazz;

	/**
	 * Set of ignored parameters.
	 */
	Set<String> ignoredParameterSet;

	/**
	 * Constructor.
	 * 
	 * @param clazz
	 *            Class to which the constraint descriptor is generated.
	 * @param ignoredParameterSet
	 *            Set of ignored parameters.
	 */
	public ConstraintDescriptorParameters(Class<?> clazz,
			Set<String> ignoredParameterSet) {
		this.clazz = clazz;
		this.ignoredParameterSet = ignoredParameterSet;
	}

	/**
	 * @return the clazz
	 */
	public final Class<?> getClazz() {
		return clazz;
	}

	/**
	 * @return the ignoredParameterSet
	 */
	public final Set<String> getIgnoredParameterSet() {
		return ignoredParameterSet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clazz == null) ? 0 : clazz.hashCode());
		result = prime
				* result
				+ ((ignoredParameterSet == null) ? 0 : ignoredParameterSet
						.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConstraintDescriptorParameters other = (ConstraintDescriptorParameters) obj;
		if (clazz == null) {
			if (other.clazz != null)
				return false;
		} else if (!clazz.equals(other.clazz))
			return false;
		if (ignoredParameterSet == null) {
			if (other.ignoredParameterSet != null)
				return false;
		} else if (!ignoredParameterSet.equals(other.ignoredParameterSet))
			return false;
		return true;
	}
}
