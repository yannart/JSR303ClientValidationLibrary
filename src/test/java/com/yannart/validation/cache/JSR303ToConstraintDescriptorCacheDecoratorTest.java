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
package com.yannart.validation.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yannart.validation.JSR303ToConstraintDescriptor;

/**
 * Test for JSR303ToConstraintDescriptorCacheDecorator.
 * 
 * @author Yann Nicolas
 */
public class JSR303ToConstraintDescriptorCacheDecoratorTest {

	JSR303ToConstraintDescriptor wrapped;
	JSR303ToConstraintDescriptorCacheDecoratorImpl cacheDecorator;

	/**
	 * Setups the objects required for the tests.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		wrapped = mock(JSR303ToConstraintDescriptor.class);
		cacheDecorator = new JSR303ToConstraintDescriptorCacheDecoratorImpl(wrapped);
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.cache.JSR303ToConstraintDescriptorCacheDecoratorImpl#JSR303ToConstraintDescriptorCacheDecorator(com.yannart.validation.JSR303ToConstraintDescriptor)}
	 * .
	 */
	@Test
	public final void testJSR303ToConstraintDescriptorCacheDecorator() {

		JSR303ToConstraintDescriptor wrapped = mock(JSR303ToConstraintDescriptor.class);
		JSR303ToConstraintDescriptorCacheDecorator deco = new JSR303ToConstraintDescriptorCacheDecoratorImpl(
				wrapped);

		assertSame(wrapped, deco.getWrapped());
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.cache.JSR303ToConstraintDescriptorCacheDecoratorImpl#render(java.lang.Class)}
	 * .
	 */
	@Test
	public final void testRenderClassOfQ() {

		// Even if calling the render with no ignored parameters, it is the
		// method that takes an array of string for ignored parameters that it
		// is called.
		when(wrapped.render(String.class, new String[] {})).thenReturn("test");

		String renderedDescriptor = cacheDecorator.render(String.class,
				new String[] {});
		assertEquals("test", renderedDescriptor);

		String renderedDescriptor2 = cacheDecorator.render(String.class);
		assertSame(renderedDescriptor, renderedDescriptor2);

		assertSame(renderedDescriptor,
				cacheDecorator.render(String.class, new String[] {}));
		assertSame(renderedDescriptor, cacheDecorator.render(String.class));

		// The wrapped object render method for particular parameters is called
		// a single time.
		verify(wrapped, times(1)).render(String.class, new String[] {});
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.cache.JSR303ToConstraintDescriptorCacheDecoratorImpl#render(java.lang.Class, java.lang.String[])}
	 * .
	 */
	@Test
	public final void testRenderClassOfQStringArray() {
		// Even if calling the render with no ignored parameters, it is the
		// method that takes an array of string for ignored parameters that it
		// is called.
		when(wrapped.render(String.class, "a", "b", "c")).thenReturn("abc");
		when(wrapped.render(String.class, "d", "e", "f", "g")).thenReturn(
				"defg");
		when(wrapped.render(List.class, "1", "2")).thenReturn("numbers");

		// First set of parameters
		String renderedDescriptor = cacheDecorator.render(String.class, "a",
				"b", "c");
		assertEquals("abc", renderedDescriptor);

		assertSame(
				"For second generation, the returned value must be the same",
				renderedDescriptor,
				cacheDecorator.render(String.class, "a", "b", "c"));

		// The wrapped object render method for particular parameters is called
		// a single time.
		verify(wrapped, times(1)).render(String.class, "a", "b", "c");

		// Second set of parameters
		renderedDescriptor = cacheDecorator.render(String.class, "d", "e", "f",
				"g");
		assertEquals("defg", renderedDescriptor);

		assertSame(
				"For second generation, the returned value must be the same",
				renderedDescriptor,
				cacheDecorator.render(String.class, "d", "e", "f", "g"));

		// The wrapped object render method for particular parameters is called
		// a single time.
		verify(wrapped, times(1)).render(String.class, "d", "e", "f", "g");

		// Third set of parameters
		renderedDescriptor = cacheDecorator.render(List.class, "1", "2");
		assertEquals("numbers", renderedDescriptor);

		assertSame(
				"For second generation, the returned value must be the same",
				renderedDescriptor, cacheDecorator.render(List.class, "1", "2"));

		// The wrapped object render method for particular parameters is called
		// a single time.
		verify(wrapped, times(1)).render(List.class, "1", "2");
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.cache.JSR303ToConstraintDescriptorCacheDecoratorImpl#render(java.lang.Class, boolean)}
	 * .
	 */
	@Test
	public final void testRenderClassOfQBoolean() {

		when(wrapped.render(String.class, new String[] {})).thenReturn("test");
		String renderedDescriptor = cacheDecorator.render(String.class);
		assertEquals("test", renderedDescriptor);

		// tests keeping the cache
		assertSame(renderedDescriptor,
				cacheDecorator.render(String.class, false));

		// the original is not called again
		verify(wrapped, times(1)).render(String.class, new String[] {});

		// resets the cache
		cacheDecorator.render(String.class, true);

		// the original wrapper have to be called another time
		verify(wrapped, times(2)).render(String.class, new String[] {});

		// call again the rendering without reseting the cache
		cacheDecorator.render(String.class);

		// the original is not called again
		verify(wrapped, times(2)).render(String.class, new String[] {});
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.cache.JSR303ToConstraintDescriptorCacheDecoratorImpl#render(java.lang.Class, boolean, java.lang.String[])}
	 * .
	 */
	@Test
	public final void testRenderClassOfQBooleanStringArray() {
		when(wrapped.render(String.class, "hello")).thenReturn("bye");
		String renderedDescriptor = cacheDecorator
				.render(String.class, "hello");
		assertEquals("bye", renderedDescriptor);

		// tests keeping the cache
		assertSame(renderedDescriptor,
				cacheDecorator.render(String.class, false, "hello"));

		// the original is not called again
		verify(wrapped, times(1)).render(String.class, "hello");

		// resets the cache
		cacheDecorator.render(String.class, true, "hello");

		// the original wrapper have to be called another time
		verify(wrapped, times(2)).render(String.class, "hello");

		// call again the rendering without reseting the cache
		cacheDecorator.render(String.class, "hello");

		// the original is not called again
		verify(wrapped, times(2)).render(String.class, "hello");
	}

}
