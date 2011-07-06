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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for the class ConstrainedProperty.
 * 
 * @author Yann Nicolas
 * 
 */
public class ConstrainedPropertyTest {

	/**
	 * Tested object.
	 */
	ConstrainedProperty constrainedProperty;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		constrainedProperty = new ConstrainedProperty("PropertyName");
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.ConstrainedProperty#ConstrainedProperty(java.lang.String)}
	 * .
	 */
	@Test
	public void testConstrainedProperty() {
		assertEquals("PropertyName", constrainedProperty.getName());
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.ConstrainedProperty#addAttribute(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testAddAttribute() {
		assertEquals(0, constrainedProperty.getAttributeNumber());
		constrainedProperty.addAttribute("attribute1", "value");
		assertEquals(1, constrainedProperty.getAttributeNumber());
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.ConstrainedProperty#getAttributeNumber()}.
	 */
	@Test
	public void testGetAttributeNumber() {
		assertEquals(0, constrainedProperty.getAttributeNumber());
		constrainedProperty.addAttribute("attribute1", "value");
		assertEquals(1, constrainedProperty.getAttributeNumber());
		constrainedProperty.addAttribute("attribute2", "value");
		constrainedProperty.addAttribute("attribute3", "value");
		constrainedProperty.addAttribute("attribute4", "value");
		assertEquals(4, constrainedProperty.getAttributeNumber());
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.ConstrainedProperty#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals("PropertyName", constrainedProperty.getName());
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.ConstrainedProperty#getAttributeMap()}.
	 */
	@Test
	public void testGetAttributeMap() {
		assertNotNull(constrainedProperty.getAttributeMap());
		assertEquals(0, constrainedProperty.getAttributeMap().size());
		constrainedProperty.addAttribute("attribute", "value");
		assertEquals("value",
				constrainedProperty.getAttributeMap().get("attribute"));
	}

}
