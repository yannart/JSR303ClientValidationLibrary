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
package com.yannart.validation.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.Set;

import javax.validation.Validation;

import org.junit.Before;
import org.junit.Test;

import com.yannart.validation.ConstrainedProperty;
import com.yannart.validation.JSR303ToConstrainedProperties;
import com.yannart.validation.User;
import com.yannart.validation.converter.ConstraintConverterFactory;
import com.yannart.validation.converter.impl.ConstraintConverterFactoryImpl;

/**
 * Test for JSR303ToConstrainedPropertiesImpl.
 * 
 * @author Yann Nicolas
 */
public class JSR303ToConstrainedPropertiesImplTest {

	JSR303ToConstrainedProperties jsr303ToConstrainedProperties;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		jsr303ToConstrainedProperties = new JSR303ToConstrainedPropertiesImpl();
		jsr303ToConstrainedProperties
				.setConverterFactory(new ConstraintConverterFactoryImpl());
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.impl.JSR303ToConstrainedPropertiesImpl#generateConstrainedProperties(java.lang.Class, javax.validation.Validator)}
	 * .
	 */
	@Test
	public void testGenerateConstrainedProperties() {

		Set<ConstrainedProperty> contraintPropertySet = jsr303ToConstrainedProperties
				.generateConstrainedProperties(User.class, Validation
						.buildDefaultValidatorFactory().getValidator());

		// 4 properties have constraints
		assertEquals(4, contraintPropertySet.size());
		for (ConstrainedProperty constrainedProperty : contraintPropertySet) {
			if (constrainedProperty.getName().equals("firstname")) {
				assertEquals("1",
						constrainedProperty.getAttributeMap().get("minlength"));
				assertEquals("20",
						constrainedProperty.getAttributeMap().get("maxlength"));
				assertEquals("true",
						constrainedProperty.getAttributeMap().get("required"));
			} else if (constrainedProperty.getName().equals("middlename")) {
				assertEquals("1",
						constrainedProperty.getAttributeMap().get("minlength"));
				assertEquals("35",
						constrainedProperty.getAttributeMap().get("maxlength"));
				assertNull(constrainedProperty.getAttributeMap()
						.get("required"));
			} else if (constrainedProperty.getName().equals("lastname")) {
				assertEquals("1",
						constrainedProperty.getAttributeMap().get("minlength"));
				assertEquals("45",
						constrainedProperty.getAttributeMap().get("maxlength"));
				assertEquals("true",
						constrainedProperty.getAttributeMap().get("required"));
			} else if (constrainedProperty.getName().equals("age")) {
				assertEquals("13",
						constrainedProperty.getAttributeMap().get("min"));
				assertEquals("110",
						constrainedProperty.getAttributeMap().get("max"));
				assertEquals("true",
						constrainedProperty.getAttributeMap().get("required"));
			}
		}

	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.impl.JSR303ToConstrainedPropertiesImpl#generateConstrainedProperties(java.lang.Class, javax.validation.Validator, java.lang.Array)}
	 * .
	 */
	@Test
	public void testGenerateConstrainedPropertiesWithIgnoredProperties() {

		String[] propertiesToIgnore = new String[] { "age", "lastname" };

		Set<ConstrainedProperty> contraintPropertySet = jsr303ToConstrainedProperties
				.generateConstrainedProperties(User.class, Validation
						.buildDefaultValidatorFactory().getValidator(),
						propertiesToIgnore);

		// 2 properties have constraints
		assertEquals(2, contraintPropertySet.size());
		for (ConstrainedProperty constrainedProperty : contraintPropertySet) {
			if (constrainedProperty.getName().equals("firstname")) {
				assertEquals("1",
						constrainedProperty.getAttributeMap().get("minlength"));
				assertEquals("20",
						constrainedProperty.getAttributeMap().get("maxlength"));
				assertEquals("true",
						constrainedProperty.getAttributeMap().get("required"));
			} else if (constrainedProperty.getName().equals("middlename")) {
				assertEquals("1",
						constrainedProperty.getAttributeMap().get("minlength"));
				assertEquals("35",
						constrainedProperty.getAttributeMap().get("maxlength"));
				assertNull(constrainedProperty.getAttributeMap()
						.get("required"));
			} else if (constrainedProperty.getName().equals("lastname")) {
				fail("The lastname property should be ignored");
			} else if (constrainedProperty.getName().equals("age")) {
				fail("The age property should be ignored");
			}
		}

	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.impl.JSR303ToConstrainedPropertiesImpl#setConverterFactory(com.yannart.validation.converter.ConstraintConverterFactory)}
	 * .
	 */
	@Test
	public void testSetConverterFactory() {

		ConstraintConverterFactory factory = new ConstraintConverterFactoryImpl();
		JSR303ToConstrainedPropertiesImpl jsr303ToConstrainedProperties = new JSR303ToConstrainedPropertiesImpl();
		jsr303ToConstrainedProperties.setConverterFactory(factory);
		assertSame(factory, jsr303ToConstrainedProperties.getConverterFactory());
	}
}
