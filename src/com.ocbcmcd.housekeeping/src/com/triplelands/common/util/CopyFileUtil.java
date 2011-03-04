package com.triplelands.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFileUtil {

	public void move(File source, File des) throws IOException {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(source);
			out = new FileOutputStream(des.getAbsolutePath());

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			try { in.close(); source.delete();} catch (Exception ex) { }
			try { out.close(); } catch (Exception ex) { }
		}
	}
}
