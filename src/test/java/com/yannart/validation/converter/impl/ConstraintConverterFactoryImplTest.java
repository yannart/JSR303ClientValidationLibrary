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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.junit.Before;
import org.junit.Test;

import com.yannart.validation.converter.JSR303ConstraintConverter;

/**
 * Test for the class ConstraintConverterFactoryImpl.
 * 
 * @author Yann Nicolas
 */
public class ConstraintConverterFactoryImplTest {

	/**
	 * Tested class.
	 */
	ConstraintConverterFactoryImpl factory;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		factory = new ConstraintConverterFactoryImpl();
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.converter.impl.ConstraintConverterFactoryImpl#getConverterMapByAnnotationClass(java.lang.Class)}
	 * .
	 */
	@Test
	public void testGetConverterMapByAnnotationClass() {
		Set<JSR303ConstraintConverter> converterSet = factory
				.getConverterMapByAnnotationClass(Size.class);
		assertEquals(1, converterSet.size());
		for (JSR303ConstraintConverter converter : converterSet) {
			assertTrue(converter instanceof SizeConverter);
		}

		converterSet = factory.getConverterMapByAnnotationClass(Max.class);
		assertEquals(1, converterSet.size());
		for (JSR303ConstraintConverter converter : converterSet) {
			assertTrue(converter instanceof MaxConverter);
		}

		converterSet = factory.getConverterMapByAnnotationClass(Min.class);
		assertEquals(1, converterSet.size());
		for (JSR303ConstraintConverter converter : converterSet) {
			assertTrue(converter instanceof MinConverter);
		}

		converterSet = factory.getConverterMapByAnnotationClass(NotNull.class);
		assertEquals(1, converterSet.size());
		for (JSR303ConstraintConverter converter : converterSet) {
			assertTrue(converter instanceof RequiredConverter);
		}
	}
}
