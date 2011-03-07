package com.ocbcmcd.housekeeping.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

	public static void moveFile(File srcFile, File destFile) throws IOException {
		if (srcFile == null) {
			throw new NullPointerException("Source must not be null");
		}
		if (destFile == null) {
			throw new NullPointerException("Destination must not be null");
		}
		if (!srcFile.exists()) {
			throw new FileNotFoundException("Source '" + srcFile
					+ "' does not exist");
		}
		if (srcFile.isDirectory()) {
			throw new IOException("Source '" + srcFile + "' is a directory");
		}
		if (destFile.exists()) {
			// throw new FileExistsException("Destination '" + destFile +
			// "' already exists");
		}
		if (destFile.isDirectory()) {
			throw new IOException("Destination '" + destFile
					+ "' is a directory");
		}

		FileInputStream from = null;
		FileOutputStream to = null;
		try {
			from = new FileInputStream(srcFile);
			to = new FileOutputStream(destFile);
			byte[] buffer = new byte[4096];
			int bytesRead;

			while ((bytesRead = from.read(buffer)) != -1)
				to.write(buffer, 0, bytesRead); // write
		} finally {
			if (from != null)
				try {
					from.close();
				} catch (IOException e) {
					;
				}
			if (to != null)
				try {
					to.close();
				} catch (IOException e) {
					;
				}
		}
		if (!srcFile.delete()) {
			throw new IOException("Failed to delete original file '" + srcFile
					+ "' after copy to '" + destFile + "'");
		}

	}
}
