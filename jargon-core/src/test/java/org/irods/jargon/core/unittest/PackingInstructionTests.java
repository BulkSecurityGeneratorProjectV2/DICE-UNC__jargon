package org.irods.jargon.core.unittest;

import org.irods.jargon.core.packinstr.AbstractIRODSPackingInstructionTest;
import org.irods.jargon.core.packinstr.CollInpTest;
import org.irods.jargon.core.packinstr.DataObjCloseInpTest;
import org.irods.jargon.core.packinstr.DataObjCopyInpTest;
import org.irods.jargon.core.packinstr.DataObjInpForMcollTest;
import org.irods.jargon.core.packinstr.DataObjInpForObjStatTest;
import org.irods.jargon.core.packinstr.DataObjInpForRegTest;
import org.irods.jargon.core.packinstr.DataObjInpForUnmountTest;
import org.irods.jargon.core.packinstr.DataObjInpForUnregisterTest;
import org.irods.jargon.core.packinstr.DataObjInpTest;
import org.irods.jargon.core.packinstr.DataObjReadTest;
import org.irods.jargon.core.packinstr.DataObjWriteInpTest;
import org.irods.jargon.core.packinstr.ExecCmdStreamCloseTest;
import org.irods.jargon.core.packinstr.ExecCmdTest;
import org.irods.jargon.core.packinstr.ExecMyRuleInp_PITest;
import org.irods.jargon.core.packinstr.FileReadInpTest;
import org.irods.jargon.core.packinstr.GenQueryInpTest;
import org.irods.jargon.core.packinstr.GeneralAdminInpTest;
import org.irods.jargon.core.packinstr.GetTempPasswordForOtherTest;
import org.irods.jargon.core.packinstr.GetTempPasswordInTest;
import org.irods.jargon.core.packinstr.ModAccessControlInpTest;
import org.irods.jargon.core.packinstr.ModAvuMetadataInpTest;
import org.irods.jargon.core.packinstr.OpenedDataObjInpTest;
import org.irods.jargon.core.packinstr.SimpleQueryInpTest;
import org.irods.jargon.core.packinstr.StructFileExtAndRegInpTest;
import org.irods.jargon.core.packinstr.TransferOptionsTest;
import org.irods.jargon.core.packinstr.UserAdminInpTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ AbstractIRODSPackingInstructionTest.class,
		GenQueryInpTest.class, DataObjInpTest.class, CollInpTest.class,
		DataObjCloseInpTest.class, DataObjCopyInpTest.class,
		DataObjWriteInpTest.class, GeneralAdminInpTest.class,
		ExecCmdTest.class, DataObjReadTest.class, OpenedDataObjInpTest.class,
		ModAvuMetadataInpTest.class, ExecMyRuleInp_PITest.class,
		UserAdminInpTest.class, StructFileExtAndRegInpTest.class,
		FileReadInpTest.class, ExecCmdStreamCloseTest.class,
		SimpleQueryInpTest.class, SimpleQueryInpTest.class,
		ModAccessControlInpTest.class, GetTempPasswordInTest.class,
		TransferOptionsTest.class, DataObjInpForObjStatTest.class,
		GetTempPasswordForOtherTest.class, DataObjInpForRegTest.class,
		DataObjInpForUnregisterTest.class, DataObjInpForMcollTest.class,
		DataObjInpForUnmountTest.class })
public class PackingInstructionTests {

}
