/**
 * 
 */
package com.yannart.validation.converter.impl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Min;

import org.junit.Before;
import org.junit.Test;

import com.yannart.validation.ConstrainedProperty;

/**
 * Test for MinConverter.
 * 
 * @author Yann Nicolas
 */
public class MinConverterTest {

	/**
	 * Tested object.
	 */
	MinConverter minConverter;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		minConverter = new MinConverter();
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.converter.impl.MinConverter#annotationClassConverted()}
	 * .
	 */
	@Test
	public void testAnnotationClassConverted() {
		assertArrayEquals(new Class[] { Min.class },
				minConverter.annotationClassConverted());
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.converter.impl.MinConverter#fillConstrainedPropertyAttributes(java.lang.annotation.Annotation, java.util.Map, com.yannart.validation.ConstrainedProperty)}
	 * .
	 */
	@Test
	public void testFillConstrainedPropertyAttributes() {
		ConstrainedProperty property = new ConstrainedProperty("property");
		Map<String, Object> attributeMap = new HashMap<String, Object>();

		Min min = mock(Min.class);
		attributeMap.put("min", 1);

		minConverter.fillConstrainedPropertyAttributes(min, attributeMap,
				property);
		assertEquals("1", property.getAttributeMap().get("min"));
	}

}
