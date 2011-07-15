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
import static org.junit.Assert.assertSame;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.junit.Test;

/**
 * Test for the class JSR303ToJSONConstraintDescriptor.
 * 
 * @author Yann Nicolas
 */
public class JSR303ToJSONConstraintDescriptorTest {

	/**
	 * Test method for
	 * {@link com.yannart.validation.json.JSR303ToJSONConstraintDescriptor#getInstance()}
	 * .
	 */
	@Test
	public final void testGetInstance() {

		// The instance created must be always the same.
		assertSame(JSR303ToJSONConstraintDescriptor.getInstance(),
				JSR303ToJSONConstraintDescriptor.getInstance());
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.json.JSR303ToJSONConstraintDescriptor#getCachedInstance()}
	 * .
	 */
	@Test
	public final void testGetCachedInstance() {

		// The instance created must be always the same.
		assertSame(JSR303ToJSONConstraintDescriptor.getCachedInstance(),
				JSR303ToJSONConstraintDescriptor.getCachedInstance());

		// The wrapped object is the same instance that can be obtained with the
		// getInstance method
		assertSame(JSR303ToJSONConstraintDescriptor.getInstance(),
				JSR303ToJSONConstraintDescriptor.getCachedInstance()
						.getWrapped());

	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.AbstractJSR303ToConstraintDescriptor#render(java.lang.Class)}
	 * .
	 */
	@Test
	public final void testRenderClassOfQ() {
		JSR303ToJSONConstraintDescriptor jsr303ToJSONConstraintDescriptor = new JSR303ToJSONConstraintDescriptor(
				false);

		String json = jsr303ToJSONConstraintDescriptor.render(Order.class);

		assertEquals(
				"rules:{alreadyPayed:{min:0,max:99},mealName:{required:true,minlength:4,maxlength:40},price:{min:0,max:99},waiterName:{required:true}}",
				json);
	}

	/**
	 * Test method for
	 * {@link com.yannart.validation.AbstractJSR303ToConstraintDescriptor#render(java.lang.Class, java.lang.String[])}
	 * .
	 */
	@Test
	public final void testRenderClassOfQStringArray() {
		JSR303ToJSONConstraintDescriptor jsr303ToJSONConstraintDescriptor = new JSR303ToJSONConstraintDescriptor(
				false);

		String json = jsr303ToJSONConstraintDescriptor.render(Order.class,
				new String[] { "waiterName" });

		assertEquals(
				"rules:{alreadyPayed:{min:0,max:99},mealName:{required:true,minlength:4,maxlength:40},price:{min:0,max:99}}",
				json);
	}

}

class Order {

	@NotNull
	@Size(min = 4, max = 40)
	String mealName;

	@NotNull
	String waiterName;

	String ignored;

	@Min(0)
	@Max(99)
	int price;

	@Min(0)
	@Max(99)
	Integer alreadyPayed;
}
