package org.irods.jargon.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.testutils.TestingPropertiesHelper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MiscIRODSUtilsTest {

	private static Properties testingProperties = new Properties();
	private static org.irods.jargon.testutils.TestingPropertiesHelper testingPropertiesHelper = new TestingPropertiesHelper();
	public static final String IRODS_TEST_SUBDIR_PATH = "MiscIRODSUtilsTest";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		org.irods.jargon.testutils.TestingPropertiesHelper testingPropertiesLoader = new TestingPropertiesHelper();
		testingProperties = testingPropertiesLoader.getTestProperties();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testIsFileInThisZoneNullPath() {
		MiscIRODSUtils.isFileInThisZone(null, testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties));
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testIsFileInThisZoneBlankPath() {
		MiscIRODSUtils.isFileInThisZone("", testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties));
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testIsFileInThisZoneNullAccount() {
		MiscIRODSUtils.isFileInThisZone("path", null);
	}

	@Test
	public final void testIsFileInThisZoneWhenInZone() throws Exception {
		String targetIrodsPath = testingPropertiesHelper
				.buildIRODSCollectionAbsolutePathFromTestProperties(
						testingProperties, IRODS_TEST_SUBDIR_PATH);
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);
		boolean inZone = MiscIRODSUtils.isFileInThisZone(targetIrodsPath,
				irodsAccount);
		Assert.assertTrue("should be in zone", inZone);
	}

	@Test
	public final void testIsFileInThisZoneWhenNotInZone() throws Exception {
		String targetIrodsPath = testingPropertiesHelper
				.buildIRODSCollectionAbsolutePathFromFederatedZoneReadTestProperties(
						testingProperties, IRODS_TEST_SUBDIR_PATH);
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);
		boolean inZone = MiscIRODSUtils.isFileInThisZone(targetIrodsPath,
				irodsAccount);
		Assert.assertFalse("should not be in zone", inZone);
	}

	@Test
	public final void testGetDefaultStorageResourceWhenInZone()
			throws Exception {
		String targetIrodsPath = testingPropertiesHelper
				.buildIRODSCollectionAbsolutePathFromTestProperties(
						testingProperties, IRODS_TEST_SUBDIR_PATH);
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);
		String defaultStorageResource = MiscIRODSUtils
				.getDefaultIRODSResourceFromAccountIfFileInZone(
						targetIrodsPath, irodsAccount);
		Assert.assertEquals("should pull default resource from IRODS account",
				irodsAccount.getDefaultStorageResource(),
				defaultStorageResource);
	}

	@Test
	public final void testGetDefaultStorageResourceWhenNotInZone()
			throws Exception {
		String targetIrodsPath = testingPropertiesHelper
				.buildIRODSCollectionAbsolutePathFromFederatedZoneReadTestProperties(
						testingProperties, IRODS_TEST_SUBDIR_PATH);
		IRODSAccount irodsAccount = testingPropertiesHelper
				.buildIRODSAccountFromTestProperties(testingProperties);
		String defaultStorageResource = MiscIRODSUtils
				.getDefaultIRODSResourceFromAccountIfFileInZone(
						targetIrodsPath, irodsAccount);
		Assert.assertEquals("should pull default resource from IRODS account",
				"", defaultStorageResource);
	}

	/**
	 * Break an iRODS abs path into components, then rebuild the whole path
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testBreakAndRebuildPathFromComponents() throws Exception {
		String targetPath = "/a/path/in/irods/here";
		List<String> pathComponents = MiscIRODSUtils
				.breakIRODSPathIntoComponents(targetPath);
		TestCase.assertEquals("did not break into right number of paths", 6,
				pathComponents.size());
		String actual = MiscIRODSUtils.buildPathFromComponentsUpToIndex(
				pathComponents, -1);
		TestCase.assertEquals("did not reconstitute path correctly",
				targetPath, actual);

	}

	/**
	 * Break an iRODS abs path into components. Null path should be error
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public final void testBuildPathFromComponentsWhenNull()
			throws Exception {
		MiscIRODSUtils.buildPathFromComponentsUpToIndex(null, -1);

	}

	/**
	 * Break an iRODS abs path into components. If the path is empty, should
	 * give '/' as the path back
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testBreakAndRebuildPathFromComponentsWhenEmpty()
			throws Exception {
		String targetPath = "/";
		List<String> pathComponents = new ArrayList<String>();

		String actual = MiscIRODSUtils.buildPathFromComponentsUpToIndex(
				pathComponents, -1);
		TestCase.assertEquals("did not reconstitute path correctly",
				targetPath, actual);

	}

	/**
	 * Break an iRODS abs path into components, then rebuild the whole path when
	 * the path is just root
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testBreakAndRebuildPathFromComponentsWhenRoot()
			throws Exception {
		String targetPath = "/";
		List<String> pathComponents = MiscIRODSUtils
				.breakIRODSPathIntoComponents(targetPath);
		TestCase.assertEquals("did not break into right number of paths", 0,
				pathComponents.size());
		String actual = MiscIRODSUtils.buildPathFromComponentsUpToIndex(
				pathComponents, -1);
		TestCase.assertEquals("did not reconstitute path correctly",
				targetPath, actual);

	}


	/**
	 * Break an iRODS abs path into components, then rebuild the path using the
	 * first 3 components
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testBreakAndRebuildPathFromComponentsGetFirstThreeParts()
			throws Exception {
		String targetPath = "/a/path/in/irods/here";
		List<String> pathComponents = MiscIRODSUtils
				.breakIRODSPathIntoComponents(targetPath);
		String actual = MiscIRODSUtils.buildPathFromComponentsUpToIndex(
				pathComponents, 3);
		TestCase.assertEquals("did not reconstitute path correctly",
				"/a/path/in", actual);

	}

	/**
	 * Break user out of user name when just a user
	 */
	@Test
	public void testGetUserInUserNameJustUserName() {
		String testUser = "justauser";
		String actual = MiscIRODSUtils.getUserInUserName(testUser);
		TestCase.assertEquals(testUser, actual);
	}

	/**
	 * Break user out of user name when in user#zone
	 */
	@Test
	public void testGetUserInUserNameWhenHasZone() {
		String testUser = "justauser#zone";
		String expected = "justauser";
		String actual = MiscIRODSUtils.getUserInUserName(testUser);
		TestCase.assertEquals(expected, actual);
	}

	/**
	 * what about if it has a user# with no zone?
	 */
	@Test
	public void testGetUserInUserNameWhenHasPoundNoZone() {
		String testUser = "justauser#";
		String expected = "justauser";
		String actual = MiscIRODSUtils.getUserInUserName(testUser);
		TestCase.assertEquals(expected, actual);
	}

	/**
	 * how about #zone
	 */
	@Test
	public void testGetUserInUserNameWhenHasPoundNoUser() {
		String testUser = "#zone";
		String expected = "";
		String actual = MiscIRODSUtils.getUserInUserName(testUser);
		TestCase.assertEquals(expected, actual);
	}

	/**
	 * how about null
	 */
	@Test
	public void testGetUserInUserNameWhenNull() {
		String testUser = null;
		String expected = "";
		String actual = MiscIRODSUtils.getUserInUserName(testUser);
		TestCase.assertEquals(expected, actual);
	}

	/**
	 * Break user out of user name when just a user
	 */
	@Test
	public void testGetZoneInUserNameJustUserName() {
		String testUser = "justauser";
		String actual = MiscIRODSUtils.getZoneInUserName(testUser);
		TestCase.assertEquals("", actual);
	}

	/**
	 * Break user out of zone name when in user#zone
	 */
	@Test
	public void testGetZoneInUserNameWhenHasZone() {
		String testUser = "justauser#zone";
		String expected = "zone";
		String actual = MiscIRODSUtils.getZoneInUserName(testUser);
		TestCase.assertEquals(expected, actual);
	}

	/**
	 * what about if it has a user# with no zone?
	 */
	@Test
	public void testGetZoneInUserNameWhenHasPoundNoZone() {
		String testUser = "justauser#";
		String expected = "";
		String actual = MiscIRODSUtils.getZoneInUserName(testUser);
		TestCase.assertEquals(expected, actual);
	}

	/**
	 * how about #zone
	 */
	@Test
	public void testGetZoneInUserNameWhenHasPoundNoUser() {
		String testUser = "#zone";
		String expected = "zone";
		String actual = MiscIRODSUtils.getZoneInUserName(testUser);
		TestCase.assertEquals(expected, actual);
	}

	/**
	 * how about null
	 */
	@Test
	public void testGetZoneInUserNameWhenNull() {
		String testUser = null;
		String expected = "";
		String actual = MiscIRODSUtils.getZoneInUserName(testUser);
		TestCase.assertEquals(expected, actual);
	}

}
