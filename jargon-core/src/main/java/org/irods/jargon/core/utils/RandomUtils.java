/**
 * 
 */
package org.irods.jargon.core.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Various utils for generating ramdomness (e.g. encryption keys used in
 * client/server negotiation)
 * 
 * @author Mike Conway - DICE
 *
 */
public class RandomUtils {

	/**
	 * Return a randomly generated byte array
	 * 
	 * @param length
	 *            <code>int</code> with length of desired array
	 * @return <code>byte[]</code> of <code>length</code> filled with random
	 *         bytes
	 */
	public static final byte[] generateRandomBytesOfLength(final int length) {
		if (length <= 0) {
			throw new IllegalArgumentException("length must be > 0");
		}

		byte[] result = new byte[length];
		ThreadLocalRandom.current().nextBytes(result);
		return result;

	}

}
