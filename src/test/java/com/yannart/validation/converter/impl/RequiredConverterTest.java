/**
 * 
 */
package com.yannart.validation.converter.impl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.junit.Before;
import org.junit.Test;

import com.yannart.validation.ConstrainedProperty;

/**
 * Test for RequiredConverter.
 * 
 * @author Yann Nicolas
 */
public class RequiredConverterTest {

	/**
	 * Tested object.
	 */
	RequiredConverter requiredConverter;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		requiredConverter = new RequiredConverter();
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.converter.impl.RequiredConverter#annotationClassConverted()}
	 * .
	 */
	@Test
	public void testAnnotationClassConverted() {
		assertArrayEquals(new Class[] { NotNull.class },
				requiredConverter.annotationClassConverted());
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.converter.impl.RequiredConverter#fillConstrainedPropertyAttributes(java.lang.annotation.Annotation, java.util.Map, com.yannart.validation.ConstrainedProperty)}
	 * .
	 */
	@Test
	public void testFillConstrainedPropertyAttributes() {
		ConstrainedProperty property = new ConstrainedProperty("property");
		Map<String, Object> attributeMap = new HashMap<String, Object>();

		NotNull notNull = mock(NotNull.class);

		requiredConverter.fillConstrainedPropertyAttributes(notNull,
				attributeMap, property);
		assertEquals("true", property.getAttributeMap().get("required"));
	}

}
