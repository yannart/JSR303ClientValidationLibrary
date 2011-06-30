/**
 * 
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
