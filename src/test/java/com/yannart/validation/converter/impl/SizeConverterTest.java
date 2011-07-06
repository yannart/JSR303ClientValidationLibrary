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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Size;

import org.junit.Before;
import org.junit.Test;

import com.yannart.validation.ConstrainedProperty;

/**
 * Test for SizeConverter.
 * 
 * @author Yann Nicolas
 */
public class SizeConverterTest {

	/**
	 * Tested object.
	 */
	SizeConverter sizeConverter;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		sizeConverter = new SizeConverter();
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.converter.impl.SizeConverter#annotationClassConverted()}
	 * .
	 */
	@Test
	public void testAnnotationClassConverted() {
		assertArrayEquals(new Class[] { Size.class },
				sizeConverter.annotationClassConverted());
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.converter.impl.SizeConverter#fillConstrainedPropertyAttributes(java.lang.annotation.Annotation, java.util.Map, com.yannart.validation.ConstrainedProperty)}
	 * .
	 */
	@Test
	public void testFillConstrainedPropertyAttributes() {

		ConstrainedProperty property = new ConstrainedProperty("property");
		Map<String, Object> attributeMap = new HashMap<String, Object>();
		Size size = mock(Size.class);

		// Test with no attribute
		sizeConverter.fillConstrainedPropertyAttributes(size, attributeMap,
				property);
		assertNull(property.getAttributeMap().get("minlength"));
		assertNull(property.getAttributeMap().get("maxlength"));

		// Test just a Max attribute
		property.getAttributeMap().clear();
		attributeMap.clear();
		attributeMap.put("max", 123);

		sizeConverter.fillConstrainedPropertyAttributes(size, attributeMap,
				property);
		assertEquals("123", property.getAttributeMap().get("maxlength"));
		assertNull(property.getAttributeMap().get("minlength"));

		// Test just a Min attribute
		property.getAttributeMap().clear();
		attributeMap.clear();
		attributeMap.put("min", 1);

		sizeConverter.fillConstrainedPropertyAttributes(size, attributeMap,
				property);
		assertEquals("1", property.getAttributeMap().get("minlength"));
		assertNull(property.getAttributeMap().get("maxlength"));

		// Test with both Min and Max attributes
		property.getAttributeMap().clear();
		attributeMap.clear();
		attributeMap.put("min", 10);
		attributeMap.put("max", 20);

		sizeConverter.fillConstrainedPropertyAttributes(size, attributeMap,
				property);
		assertEquals("10", property.getAttributeMap().get("minlength"));
		assertEquals("20", property.getAttributeMap().get("maxlength"));
	}

}
