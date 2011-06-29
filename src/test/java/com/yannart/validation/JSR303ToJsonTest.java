/**
 * 
 */
package com.yannart.validation;

import static org.junit.Assert.*;

import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

/**
 * Manual test to verify that the JSON generation works as expected.
 * 
 * @author Yann Nicolas
 */
public class JSR303ToJsonTest {

	@Test
	public void testAll() {

		Validator validator = Validation.buildDefaultValidatorFactory()
				.getValidator();

		String json = new JSONGenerator()
				.renderJSON(new JSR303ToConstrainedProperties()
						.generateConstrainedProperties(User.class, validator));
		System.out.println(json);
	}
}
