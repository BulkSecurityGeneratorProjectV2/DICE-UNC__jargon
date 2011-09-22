/**
 * 
 */
package org.irods.jargon.core.connection;

import org.irods.jargon.core.exception.AuthenticationException;
import org.irods.jargon.core.exception.CollectionNotEmptyException;
import org.irods.jargon.core.exception.DataNotFoundException;
import org.irods.jargon.core.exception.DuplicateDataException;
import org.irods.jargon.core.exception.FileIntegrityException;
import org.irods.jargon.core.exception.InvalidUserException;
import org.irods.jargon.core.exception.JargonException;
import org.irods.jargon.core.exception.JargonFileOrCollAlreadyExistsException;
import org.irods.jargon.core.exception.NoAPIPrivException;
import org.irods.jargon.core.protovalues.ErrorEnum;

/**
 * This object is interposed in the process of interpreting the iRODS responses
 * to various protocol operations. The job of this class is to inspect the iRODS
 * response, and to detect and throw appropriate Exceptions based on the status
 * of the iRODS response. Specifically, this object detects iRODS error codes in
 * the 'intInfo' part of the response, and maps them to Jargon exceptions.
 * <p/>
 * Note that this is an early implementation, and a fuller error hierarchy will
 * develop over time.
 * 
 * @author Mike Conway - DICE (www.irods.org)
 * 
 */
public class IRODSErrorScanner {

	public static void inspectAndThrowIfNeeded(final int infoValue)
			throws JargonException {

		if (infoValue == 0) {
			return;
		}

		ErrorEnum errorEnum;

		try {
			errorEnum = ErrorEnum.valueOf(infoValue);
		} catch (IllegalArgumentException ie) {
			throw new JargonException(
					"error code received from iRODS, not in ErrorEnum translation table",
					infoValue);
		}

		// non-zero value, create appropriate exception

		switch (errorEnum) {
		case OVERWITE_WITHOUT_FORCE_FLAG:
			throw new JargonFileOrCollAlreadyExistsException(
					"Attempt to overwrite file without force flag.", infoValue);
		case CAT_INVALID_AUTHENTICATION:
			throw new AuthenticationException("AuthenticationException",
					infoValue);
		case CAT_INVALID_USER:
			throw new InvalidUserException("InvalidUserException");
		case SYS_NO_API_PRIV:
			throw new NoAPIPrivException("User lacks privileges to invoke the given API");
		case CAT_NO_ROWS_FOUND:
			throw new DataNotFoundException("no data found");
		case CAT_NAME_EXISTS_AS_COLLECTION:
			throw new JargonFileOrCollAlreadyExistsException(
					"Collection already exists", infoValue);
		case CAT_NAME_EXISTS_AS_DATAOBJ:
			throw new JargonFileOrCollAlreadyExistsException(
					"Attempt to overwrite file without force flag", infoValue);
		case CATALOG_ALREADY_HAS_ITEM_BY_THAT_NAME:
			throw new DuplicateDataException(
					"Catalog already has item by that name");
		case USER_CHKSUM_MISMATCH:
			throw new FileIntegrityException(
					"File checksum verification mismatch");
		case CAT_UNKNOWN_FILE:
			throw new DataNotFoundException("unknown file");
		case CAT_UNKNOWN_COLLECTION:
			throw new DataNotFoundException("unknown collection");
		case CAT_COLLECTION_NOT_EMPTY:
			throw new CollectionNotEmptyException("collection not empty", infoValue);
		default:
			throw new JargonException("error code recieved from iRODS:" + infoValue,
					infoValue);
		}

	}

}