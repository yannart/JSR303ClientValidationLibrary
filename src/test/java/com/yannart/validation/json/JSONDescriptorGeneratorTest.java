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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.yannart.validation.ConstrainedProperty;

/**
 * Test for JSONDescriptorGenerator.
 * 
 * @author Yann Nicolas.
 */
public class JSONDescriptorGeneratorTest {

	/**
	 * Set of ConstrainedProperty to use with the tests.
	 */
	Set<ConstrainedProperty> constrainedPropertySet;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		ConstrainedProperty constrainedProperty1 = new ConstrainedProperty(
				"test");
		constrainedProperty1.addAttribute("att", "val");
		constrainedProperty1.addAttribute("othe", "val2");
		constrainedProperty1.addAttribute("zip", "val3");

		ConstrainedProperty constrainedProperty2 = new ConstrainedProperty(
				"prop2");
		constrainedProperty2.addAttribute("att1", "val1");
		constrainedProperty2.addAttribute("att2", "val2");

		constrainedPropertySet = new LinkedHashSet<ConstrainedProperty>();
		constrainedPropertySet.add(constrainedProperty1);
		constrainedPropertySet.add(constrainedProperty2);
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.json.JSONDescriptorGenerator#JSONDescriptorGenerator(boolean)}
	 * .
	 */
	@Test
	public void testJSONDescriptorGenerator() {

		// Test with no formatting
		JSONDescriptorGenerator jsonDescriptorGenerator = new JSONDescriptorGenerator(
				false);
		assertFalse(jsonDescriptorGenerator.isFormat());

		// Test with formatting
		JSONDescriptorGenerator jsonDescriptorGenerator2 = new JSONDescriptorGenerator(
				true);
		assertTrue(jsonDescriptorGenerator2.isFormat());
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.json.JSONDescriptorGenerator#render(java.util.Set)}
	 * .
	 */
	@Test
	public void testRenderSetOfConstrainedProperty() {

		// Test with no formatting
		JSONDescriptorGenerator jsonDescriptorGenerator = new JSONDescriptorGenerator(
				false);

		assertEquals(
				jsonDescriptorGenerator.render(constrainedPropertySet, false),
				jsonDescriptorGenerator.render(constrainedPropertySet));

		// Test with formatting
		JSONDescriptorGenerator jsonDescriptorGenerator2 = new JSONDescriptorGenerator(
				true);

		assertEquals(
				jsonDescriptorGenerator2.render(constrainedPropertySet, true),
				jsonDescriptorGenerator2.render(constrainedPropertySet));

	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.json.JSONDescriptorGenerator#render(java.util.Set, boolean)}
	 * .
	 */
	@Test
	public void testRenderSetOfConstrainedPropertyTrue() {

		// With a JSON descriptor generator with no formatting
		JSONDescriptorGenerator jsonDescriptorGenerator = new JSONDescriptorGenerator(
				false);

		// Force the format
		String json = jsonDescriptorGenerator.render(constrainedPropertySet,
				true);

		assertEquals(
				"rules: {\r\n\ttest: {\r\n\t\tatt: val,\r\n\t\tothe: val2,\r\n\t\tzip: val3\r\n\t},\r\n\tprop2: {\r\n\t\tatt1: val1,\r\n\t\tatt2: val2\r\n\t}\r\n}",
				json);
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.json.JSONDescriptorGenerator#render(java.util.Set, boolean)}
	 * .
	 */
	@Test
	public void testRenderSetOfConstrainedPropertyFalse() {

		// With a JSON descriptor generator with formatting
		JSONDescriptorGenerator jsonDescriptorGenerator = new JSONDescriptorGenerator(
				true);

		// Force to don't format
		String json = jsonDescriptorGenerator.render(constrainedPropertySet,
				false);

		assertEquals(
				"rules:{test:{att:val,othe:val2,zip:val3},prop2:{att1:val1,att2:val2}}",
				json);

	}

}
