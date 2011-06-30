/**
 * 
 */
package com.yannart.validation.converter.impl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Max;

import org.junit.Before;
import org.junit.Test;

import com.yannart.validation.ConstrainedProperty;

/**
 * Test for MaxConverter.
 * 
 * @author Yann Nicolas
 */
public class MaxConverterTest {

	/**
	 * Tested object.
	 */
	MaxConverter maxConverter;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		maxConverter = new MaxConverter();
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.converter.impl.MaxConverter#annotationClassConverted()}
	 * .
	 */
	@Test
	public void testAnnotationClassConverted() {
		assertArrayEquals(new Class[] { Max.class },
				maxConverter.annotationClassConverted());
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.converter.impl.MaxConverter#fillConstrainedPropertyAttributes(java.lang.annotation.Annotation, java.util.Map, com.yannart.validation.ConstrainedProperty)}
	 * .
	 */
	@Test
	public void testFillConstrainedPropertyAttributes() {

		ConstrainedProperty property = new ConstrainedProperty("property");
		Map<String, Object> attributeMap = new HashMap<String, Object>();

		Max max = mock(Max.class);
		attributeMap.put("max", 10);

		maxConverter.fillConstrainedPropertyAttributes(max, attributeMap,
				property);
		assertEquals("10", property.getAttributeMap().get("max"));
	}

}
