package org.irods.jargon.core.packinstr;


import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetTempPasswordInTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testInstance() throws Exception {
		GetTempPasswordIn instance = GetTempPasswordIn.instance();
		TestCase.assertNotNull("null instance returned", instance);
		TestCase.assertEquals("wrong API number", GetTempPasswordIn.GET_TEMP_PASSWORD_API_NBR, instance.getApiNumber());
	}

}
