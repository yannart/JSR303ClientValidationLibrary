/**
 * 
 */
package com.yannart.validation;

import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

import com.yannart.validation.converter.impl.ConstraintConverterFactoryImpl;
import com.yannart.validation.impl.JSR303ToConstrainedPropertiesImpl;
import com.yannart.validation.json.JSONDescriptorGenerator;

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

		JSR303ToConstrainedProperties jsr303ToConstrainedProperties = new JSR303ToConstrainedPropertiesImpl();
		jsr303ToConstrainedProperties.setConverterFactory(new ConstraintConverterFactoryImpl());
		
		String json = new JSONDescriptorGenerator(false)
				.render(jsr303ToConstrainedProperties
						.generateConstrainedProperties(User.class, validator));
		System.out.println(json);
	}
}
