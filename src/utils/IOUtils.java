package utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class IOUtils {
	public static String readFile(File file) throws IOException{
		FileInputStream in = new FileInputStream(file);
		byte[] data = toByteArray(in);
		return new String(data);
		
	}
	
	 public static byte[] toByteArray(InputStream input)
	            throws IOException
	    {
	        ByteArrayOutputStream output = new ByteArrayOutputStream();
	        copy(input, output);
	        return output.toByteArray();
	    }

	    private static int copy(InputStream input, OutputStream output)
	            throws IOException
	    {
	        long count = copyLarge(input, output);
	        if (count > 2147483647L) {
	            return -1;
	        }
	        return (int)count;
	    }

	    private static long copyLarge(InputStream input, OutputStream output)
	            throws IOException
	    {
	        byte[] buffer = new byte[4096];
	        long count = 0L;
	        int n = 0;
	        while (-1 != (n = input.read(buffer))) {
	            output.write(buffer, 0, n);
	            count += n;
	        }
	        return count;
	    }
}
