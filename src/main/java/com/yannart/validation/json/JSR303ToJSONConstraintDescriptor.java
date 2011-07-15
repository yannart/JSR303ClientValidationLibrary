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
package com.yannart.validation.json;

import com.yannart.validation.AbstractJSR303ToConstraintDescriptor;
import com.yannart.validation.cache.JSR303ToConstraintDescriptorCacheDecorator;
import com.yannart.validation.cache.JSR303ToConstraintDescriptorCacheDecoratorImpl;

/**
 * Generates a JSON constraint descriptor following the syntax of the client
 * validation library "JQuery Validation"
 * http://bassistance.de/jquery-plugins/jquery-plugin-validation/
 * 
 * @author Yann Nicolas
 */
public class JSR303ToJSONConstraintDescriptor extends
		AbstractJSR303ToConstraintDescriptor {

	/**
	 * Instance.
	 */
	private static JSR303ToJSONConstraintDescriptor instance;

	/**
	 * Instance with caching capability.
	 */
	private static JSR303ToConstraintDescriptorCacheDecorator instanceWithCache;

	/**
	 * Constructor where the output JSON is formatted.
	 */
	public JSR303ToJSONConstraintDescriptor() {
		this(true);
	}

	/**
	 * Constructor with the definition of the output formatting.
	 * 
	 * @param format
	 *            flag to indicate if the JSON code must be formatted or not.
	 *            True to format the output, false to not format the output.
	 */
	public JSR303ToJSONConstraintDescriptor(final boolean format) {
		super(new JSONDescriptorGenerator(format));
	}

	/**
	 * Obtain an instance of JSR303ToJSONConstraintDescriptor. If an instance
	 * already has been created, it reuses it.
	 * 
	 * @return an instance of JSR303ToJSONConstraintDescriptor.
	 */
	public static JSR303ToJSONConstraintDescriptor getInstance() {
		if (instance == null) {
			instance = new JSR303ToJSONConstraintDescriptor();
		}
		return instance;
	}

	/**
	 * Obtain an instance of JSR303ToJSONConstraintDescriptor with the
	 * capability of caching the requests. If an instance already has been
	 * created, it reuses it.
	 * 
	 * @return an instance of JSR303ToJSONConstraintDescriptor with caching
	 *         capability
	 */
	public static JSR303ToConstraintDescriptorCacheDecorator getCachedInstance() {
		if (instanceWithCache == null) {
			instanceWithCache = new JSR303ToConstraintDescriptorCacheDecoratorImpl(
					getInstance());
		}
		return instanceWithCache;
	}

}
