/**
 * 
 */
package com.yannart.validation;

import javax.validation.constraints.*;

/**
 * User annotated with JSR303 used in tests.
 * 
 * @author Yann Nicolas
 * 
 */
public class User {

	@NotNull
	@Size(min = 1, max = 20)
	private String firstname;

	@Size(min = 1, max = 35)
	private String middlename;

	@NotNull
	@Size(min = 1, max = 45)
	private String lastname;

	@NotNull
	@Min(13L)
	@Max(110L)
	private Integer age;

	private boolean enabled;

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the middlename
	 */
	public String getMiddlename() {
		return middlename;
	}

	/**
	 * @param middlename
	 *            the middlename to set
	 */
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
