package com.lhsws.gak.util;

import java.io.File;

/**
 * @author akash.meshram
 *
 */

public interface Config {

	public String formatZipEntry(File f);

	public boolean matchFile(File f);

	public boolean processRecursive();

}
