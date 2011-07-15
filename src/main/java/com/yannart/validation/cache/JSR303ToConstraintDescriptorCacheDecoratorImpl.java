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

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.yannart.validation.JSR303ToConstraintDescriptor;

/**
 * Implementation of a decorator of JSR303ToConstraintDescriptor adding caching
 * support to avoid the regeneration of constraint descriptors when it already
 * have been done.
 * 
 * @author Yann Nicolas.
 */
public class JSR303ToConstraintDescriptorCacheDecoratorImpl implements
		JSR303ToConstraintDescriptor,
		JSR303ToConstraintDescriptorCacheDecorator {

	/**
	 * Wrapped JSR303ToConstraintDescriptor.
	 */
	private JSR303ToConstraintDescriptor wrappedJsr303ToConstraintDescriptor;

	/**
	 * Map that maps a generated constraint descriptor with the parameters that
	 * generates it.
	 */
	private Map<ConstraintDescriptorParameters, String> descriptorByParametersMap = new HashMap<ConstraintDescriptorParameters, String>();

	/**
	 * Constructor where the wrapped jsr303ToConstraintDescriptor is set.
	 */
	public JSR303ToConstraintDescriptorCacheDecoratorImpl(
			final JSR303ToConstraintDescriptor wrappedJsr303ToConstraintDescriptor) {
		this.wrappedJsr303ToConstraintDescriptor = wrappedJsr303ToConstraintDescriptor;
	}

	/**
	 * {@inheritDoc}
	 */
	public String render(final Class<?> clazz) {

		// Call the overloaded method with an empty array of properties to
		// ignore.
		return render(clazz, new String[] {});
	}

	/**
	 * {@inheritDoc}
	 */
	public String render(final Class<?> clazz,
			final String... propertiesToIgnore) {

		ConstraintDescriptorParameters constraintDescriptorParameters = generateConstraintDescriptorParameters(
				clazz, propertiesToIgnore);

		String constraintDescriptor = null;

		// Synchronized on the map for concurrent access
		synchronized (descriptorByParametersMap) {
			constraintDescriptor = descriptorByParametersMap
					.get(constraintDescriptorParameters);

			// no constraint descriptor cached, generates the output and stores
			// it in cache.
			if (constraintDescriptor == null) {
				constraintDescriptor = wrappedJsr303ToConstraintDescriptor
						.render(clazz, propertiesToIgnore);
				descriptorByParametersMap.put(constraintDescriptorParameters,
						constraintDescriptor);
			}
		}

		return constraintDescriptor;
	}

	/**
	 * {@inheritDoc}
	 */
	public String render(final Class<?> clazz, final boolean resetCache) {

		// calls the method with an empty array of properties to ignore
		return render(clazz, resetCache, new String[] {});
	}

	/**
	 * {@inheritDoc}
	 */
	public String render(final Class<?> clazz, final boolean resetCache,
			final String... propertiesToIgnore) {

		// If the cache reseting is enabled, removes from the cache
		if (resetCache) {
			ConstraintDescriptorParameters constraintDescriptorParameters = generateConstraintDescriptorParameters(
					clazz, propertiesToIgnore);

			synchronized (descriptorByParametersMap) {
				descriptorByParametersMap
						.remove(constraintDescriptorParameters);
			}
		}

		// Call the method to regenerate the cache
		return render(clazz, propertiesToIgnore);
	}

	/**
	 * Generates a ConstraintDescriptorParameters from the class and the
	 * properties to ignore.
	 * 
	 * @param clazz
	 *            class of the bean
	 * @param propertiesToIgnore
	 *            properties to ignore in the generation
	 * @return the ConstraintDescriptorParameters
	 */
	private ConstraintDescriptorParameters generateConstraintDescriptorParameters(
			Class<?> clazz, String... propertiesToIgnore) {
		Set<String> propertiesToIgnoreSet = new HashSet<String>(
				Arrays.asList(propertiesToIgnore));

		return new ConstraintDescriptorParameters(clazz, propertiesToIgnoreSet);
	}

	/**
	 * {@inheritDoc}
	 */
	public final JSR303ToConstraintDescriptor getWrapped() {
		return wrappedJsr303ToConstraintDescriptor;
	}
}
