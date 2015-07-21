package io.manasobi.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import io.manasobi.exception.ByteUtilsException;

/**
 * Byte[] 와 관련된 변환 및 조작 기능들을 제공한다.<br>
 * 
 * @author manasobi
 * @since 1.0.0
 */
public final class ByteUtils {
	
	private ByteUtils() { }
	
	private static final int RADIX_16 = 16;
	private static final int RADIX_10 = 10;
	private static final int RADIX_8 = 8;
    
	private static final int UNSIGNED_8BIT_MAX = 0xFF;
	
    /**
	 * <p>두 배열의 값이 동일한지 비교한다.</p>
	 * 
	 * <pre>
	 * ArrayUtils.equals(null, null)                        = true
	 * ArrayUtils.equals(["one", "two"], ["one", "two"])    = true
	 * ArrayUtils.equals(["one", "two"], ["three", "four"]) = false
	 * </pre>
	 * 
	 * @param array1 비교할 byte배열 대상 1
	 * @param array2 비교할 byte배열 대상 2
	 * @return 동일하면 <code>true</code>, 아니면 <code>false</code>
	 */
	public static boolean equals(byte[] array1, byte[] array2) {
		
		if (array1 == array2) {
			return true;
		}
		
		if (array1 == null || array2 == null) {
			return false;
		}
		
		if (array1.length != array2.length) {
			return false;
		}
		
		for (int i = 0; i < array1.length; i++) {
			if (array1[i] != array2[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
     * <p>16진수 문자열을 바이트 배열로 변환한다.</p> 
     * <p>문자열의 2자리가 하나의 byte로 바뀐다.</p>
     * 
     * <pre> ByteUtils.hexStringToBytes(null) = null
     * ByteUtils.hexStringToBytes("0E1F4E") = [0x0e, 0xf4, 0x4e]
     * ByteUtils.hexStringToBytes("48414e") = [0x48, 0x41, 0x4e] </pre>
     * 
     * @param digits
     *            16진수 문자열
     * @return 16진수 문자열을 변환한 byte 배열
     */
    public static byte[] hexStringToBytes(String digits) {
    	
        if (digits == null) { 
        	return null; 
        }
        
        int length = digits.length();
        
        if (length % 2 == 1) {
        	throw new ByteUtilsException(digits + "의 자릿수는 짝수이어야 합니다.");
        }
        
        length = length / 2;
        
        byte[] bytes = new byte[length];
        
        for (int i = 0; i < length; i++) {
            int index = i * 2;
            bytes[i] = (byte) (Short.parseShort(digits.substring(index, index + 2), RADIX_16));
        }
        
        return bytes;
    }
	
	public static String printPrettyHex(byte[] bytes) {
		
		String hexStr = toHexString(bytes);
		
		return printPrettyHex(hexStr);
	}
	
	public static String printPrettyHex(String hexStr) {
		
		List<String> hexUnitList = new ArrayList<String>();
		
		int beginIndex = 0;
		int endIndex = beginIndex + 2;
		
		while (hexStr.length() > 0) {
						
			hexUnitList.add(hexStr.substring(beginIndex, endIndex));			
			
			hexStr = hexStr.substring(endIndex);
		}
		
		String result = "";
		
		int i = 1;
		
		for (String hexUnit : hexUnitList) {
			
			result += hexUnit + " ";
			
			if (i % 8 == 0) {
				
				if (i % 16 == 0) {
					result += "\r\n";
				} else {
					result += " ";					
				}
			}
			
			i++;
		}
		
		return result;
	}

	public static byte[] toByte(String data) {
		return data.getBytes();
	}
	
    /**
	 * <p>문자열을 바이트로 변환한다.</p>
	 * 
	 * <pre>
	 * ByteUtils.toByte("1", *)    = 0x01
	 * ByteUtils.toByte("-1", *)   = 0xff
	 * ByteUtils.toByte("a", 0x00) = 0x00
	 * </pre>
	 * 
	 * @param value 10진수 문자열 값
	 * @param defaultValue 예외 발생시 기본으로 반환할 바이트
	 * @return 바이트로 변환된 문자열
	 */
	public static byte toByte(String value, byte defaultValue) {
		try {
			return Byte.parseByte(value);	
		} catch (Exception e) {
			throw new ByteUtilsException(e.getMessage());
		}
	}

    public static byte[] toByte(String data, String charset) {
		try {
			return data.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new ByteUtilsException(e.getMessage());
		}
	}
	
	/**
	 * <p>문자열을 바이트로 변환한다.</p>
	 * 
	 * <pre>
	 * ByteUtils.toByteObject("1", *)    = 0x01
	 * ByteUtils.toByteObject("-1", *)   = 0xff
	 * ByteUtils.toByteObject("a", 0x00) = 0x00
	 * </pre>
	 * 
	 * @param value 10진수 문자열 값
	 * @param defaultValue 예외 발생시 기본으로 반환할 바이트
	 * @return Byte Object로 변환된 문자열
	 */
	public static Byte toByteObject(String value, Byte defaultValue) {
		try {
			return new Byte(value);
		} catch (Exception e) {
			throw new ByteUtilsException(e.getMessage());
		}
	}

	/**
	 * <p>int 형의 값을 바이트 배열(4바이트)로 변환한다.</p>
	 * 
	 * @param value 바이트 배열로 변환할 int 값
	 * @return 바이트 배열(4바이트)
	 */
	public static byte[] toBytes(int value) {
		byte[] dest = new byte[4];
		toBytes(value, dest, 0);
		return dest;
	}

    
    /**
	 * <p>int 형의 값을 바이트 배열(4바이트)로 변환한다.</p>
	 * 
	 * @param value 바이트 배열로 변환할 int 값
	 * @param dest 변환할 바이트 배열
	 * @param destPos 바이트 포지션
	 */
	public static void toBytes(int value, byte[] dest, int destPos) {
		for (int i = 0; i < 4; i++) {
			dest[i + destPos] = (byte) (value >> ((7 - i) * 8));
		}
	}

	/**
	 * <p>long 형의 값을 바이트 배열(8바이트)로 변환한다.</p>
	 * 
	 * @param value 바이트 배열로 변환할 long 값
	 * @return 바이트 배열(8바이트)
	 */
	public static byte[] toBytes(long value) {
		byte[] dest = new byte[8];
		toBytes(value, dest, 0);
		return dest;
	}
	
	/**
	 * <p>long 형의 값을 바이트 배열(8바이트)로 변환한다.</p>
	 * 
	 * @param value 바이트 배열로 변환할 long 값
	 * @param dest 변환할 바이트 배열
	 * @param destPos 바이트 포지션
	 */
	public static void toBytes(long value, byte[] dest, int destPos) {
		for (int i = 0; i < 8; i++) {
			dest[i + destPos] = (byte) (value >> ((7 - i) * 8));
		}
	}
	
	/**
	 * <p>8, 10, 16진수 문자열을 바이트 배열로 변환한다.</p>
	 * <p>8, 10진수인 경우는 문자열의 3자리가, 16진수인 경우는 2자리가, 하나의 byte로 바뀐다.</p>
	 * 
	 * <pre>
	 * ByteUtils.toBytes(null)     = null
	 * ByteUtils.toBytes("0E1F4E", 16) = [0x0e, 0xf4, 0x4e]
	 * ByteUtils.toBytes("48414e", 16) = [0x48, 0x41, 0x4e]
	 * </pre>
	 * 
	 * @param digits 문자열
	 * @param radix 진수(8, 10, 16만 가능)
	 * @return 변환된 바이트 배열
	 */
	public static byte[] toBytes(String digits, int radix) {
		
		if (digits == null) {
			return null;
		}
		
		if (radix != RADIX_16 && radix != RADIX_10 && radix != RADIX_8) {
			throw new IllegalArgumentException("For input radix: \"" + radix + "\"");
		}
		
		int divLen = (radix == RADIX_16) ? 2 : 3;
		
    	int length = digits.length();
    	
    	if (length % divLen == 1) {
    		throw new IllegalArgumentException("For input string: \"" + digits + "\"");
    	}
    	
    	length = length / divLen;
    	
    	byte[] bytes = new byte[length];
    	
    	for (int i = 0; i < length; i++) {
    		int index = i * divLen;
    		bytes[i] = (byte) (Short.parseShort(digits.substring(index, index + divLen), radix));
    	} 
    	
    	return bytes;
	}
	
	/**
	 * <p>16진수 문자열을 바이트 배열로 변환한다.</p>
	 * <p>문자열의 2자리가 하나의 byte로 바뀐다.</p>
	 * 
	 * <pre>
	 * ByteUtils.toBytesFromHexString(null)     = null
	 * ByteUtils.toBytesFromHexString("0E1F4E") = [0x0e, 0xf4, 0x4e]
	 * ByteUtils.toBytesFromHexString("48414e") = [0x48, 0x41, 0x4e]
	 * </pre>
	 * 
	 * @param digits 16진수 문자열
	 * @return 변환된 바이트 배열
	 */
	public static byte[] toBytesFromHexString(String digits) {
		
		if (digits == null) {
			return null;
		}
		
    	int length = digits.length();
    	
    	if (length % 2 == 1) {
    		throw new IllegalArgumentException("For input string: \"" + digits + "\"");
    	}
    	
    	
    	length = length / 2;
    	
    	byte[] bytes = new byte[length];
    	
    	for (int i = 0; i < length; i++) {
    		int index = i * 2;
    		bytes[i] = (byte) (Short.parseShort(digits.substring(index, index + 2), RADIX_16));
    	}
    	return bytes;
	}

	/**
	 * <p>unsigned byte(바이트)를 16진수 문자열로 바꾼다.</p>
	 * 
	 * ByteUtils.toHexString((byte)1)   = "01"
	 * ByteUtils.toHexString((byte)255) = "ff"
	 * 
	 * @param b unsigned byte
	 * @return 변환된 16진수 문자열
	 */
	public static String toHexString(byte b) {
		
		StringBuffer result = new StringBuffer(3);		
		result.append(Integer.toString((b & 0xF0) >> 4, RADIX_16));
		result.append(Integer.toString(b & 0x0F, RADIX_16));
		return result.toString();
	}
	
	/**
	 * <p>unsigned byte(바이트) 배열을 16진수 문자열로 바꾼다.</p>
	 * 
	 * <pre>
	 * ByteUtils.toHexString(null)                   = null
	 * ByteUtils.toHexString([(byte)1, (byte)255])   = "01ff"
	 * </pre>
	 * 
	 * @param bytes unsigned byte's array
	 * @return 변환된 16진수 문자열
	 */
	public static String toHexString(byte[] bytes) {
		
		if (bytes == null) {
			return null;
		}
		
		StringBuffer result = new StringBuffer();
		for (byte b : bytes) {
			result.append(Integer.toString((b & 0xF0) >> 4, RADIX_16));
			result.append(Integer.toString(b & 0x0F, RADIX_16));
		}
		return result.toString();
	}
	
	/**
	 * <p>unsigned byte(바이트) 배열을 16진수 문자열로 바꾼다.</p>
	 * 
	 * <pre>
	 * ByteUtils.toHexString(null, *, *)                   = null
	 * ByteUtils.toHexString([(byte)1, (byte)255], 0, 2)   = "01ff"
	 * ByteUtils.toHexString([(byte)1, (byte)255], 0, 1)   = "01"
	 * ByteUtils.toHexString([(byte)1, (byte)255], 1, 2)   = "ff"
	 * </pre>
	 * 
	 * @param bytes unsigned byte's array
	 * @param offset 가져올 문자의 오프셋
	 * @param length offset으로부터의 문자 길이만큼 가져옴
	 * @return 변환된 16진수 문자열
	 */
	public static String toHexString(byte[] bytes, int offset, int length) {
		if (bytes == null) {
			return null;
		}
		
		StringBuffer result = new StringBuffer();
		for (int i = offset; i < offset + length; i++) {
			result.append(Integer.toString((bytes[i] & 0xF0) >> 4, RADIX_16));
			result.append(Integer.toString(bytes[i] & 0x0F, RADIX_16));
		}
		return result.toString();
	}
	
	/**
	 * <p>입력한 바이트 배열(4바이트)을 int 형으로 변환한다.</p>
	 * 
	 * @param src 바이트 배열(4바이트)
	 * @return int형의 값
	 */
	public static int toInt(byte[] src) {
		return toInt(src, 0);
	}
	
	/**
	 * <p>입력한 바이트 배열(4바이트)을 int 형으로 변환한다.</p>
	 * 
	 * @param src 바이트 배열(4바이트)
	 * @param srcPos 바이트 포지션
	 * @return int형의 값
	 */
	public static int toInt(byte[] src, int srcPos) {
		int dword = 0;
		for (int i = 0; i < 4; i++) {
			dword = (dword << 8) + (src[i + srcPos] & UNSIGNED_8BIT_MAX);
		}
		return dword;
	}
	
	/**
	 * <p>입력한 바이트 배열(8바이트)을 long 형으로 변환한다.</p>
	 * 
	 * @param src 바이트 배열(8바이트)
	 * @return long형의 값
	 */
	public static long toLong(byte[] src) {
		return toLong(src, 0);
	}
	
	/**
	 * <p>입력한 바이트 배열(8바이트)을 long 형으로 변환한다.</p>
	 * 
	 * @param src 바이트 배열(8바이트)
	 * @param srcPos 바이트 포지션
	 * @return long형의 값
	 */
	public static long toLong(byte[] src, int srcPos) {
		long qword = 0;
		for (int i = 0; i < 8; i++) {
			qword = (qword << 8) + (src[i + srcPos] & UNSIGNED_8BIT_MAX);
		}
		return qword;
	}
	
	/**
	 * <p>입력한 바이트 배열을 문자열로 변환한다.</p> 
	 * 
	 * @param bytes 바이트 배열
	 * @return 변환된 문자열
	 */
	public static String toString(byte[] bytes) {		
		return new String(bytes);		
	}
	
	/**
	 * <p>입력한 바이트 배열을 문자열로 변환한다.</p>
	 * 
	 * @param bytes 바이트 배열
	 * @param charset 인코딩에 사용될 문자셋
	 * @return charset으로 인코딩된 문자열
	 */
	public static String toString(byte[] bytes, String charset) {		
		try {
			return new String(bytes, charset);
		} catch (UnsupportedEncodingException e) {
			throw new ByteUtilsException(e.getMessage());
		}		
	}
    
	/**
     * singed byte를 unsinged byte로 변환한다.<br>
     * Java에는 unsinged 타입이 없기때문에, int로 반환한다.
     * 
     * @param b singed byte
     * @return unsinged byte 
     */
	public static int unsignedByte(byte b) {
		return  b & UNSIGNED_8BIT_MAX;
	}
	
	/* 추후 정리 필요.
	 * private static final int DEFAULT_CHUNK_SIZE = 1024;

	  *//**
	   * save bytes to file
	   * @param fileName the file to write the supplied bytes
	   * @param theBytes the bytes to write to file
	   * @throws java.io.IOException reports problems saving bytes to file
	   *//*
	public static void saveBytesToFile(String fileName, byte[] theBytes) throws java.io.IOException {
	    saveBytesToStream( new java.io.FileOutputStream( fileName ), theBytes );
	  }

	  *//**
	   * save bytes to output stream and close the output stream on success and
	   * on failure.
	   * @param out the output stream to write the supplied bytes
	   * @param theBytes the bytes to write out
	   * @throws java.io.IOException reports problems saving bytes to output stream
	   *//*
	  public static void saveBytesToStream( java.io.OutputStream out, byte[] theBytes )
	      throws java.io.IOException {
	    try {
	      out.write( theBytes );
	    }
	    finally {
	      out.flush();
	      out.close();
	    }
	  }

	  *//**
	   * Loads bytes from the file
	   *
	   * @param fileName file to read the bytes from
	   * @return bytes read from the file
	   * @exception java.io.IOException reports IO failures
	   *//*
	  public static byte[] loadBytesFromFile( String fileName ) throws java.io.IOException {
	    return loadBytesFromStream( new java.io.FileInputStream( fileName ), DEFAULT_CHUNK_SIZE );
	  }

	  *//**
	   * Loads bytes from the given input stream until the end of stream
	   * is reached.  It reads in at kDEFAULT_CHUNK_SIZE chunks.
	   *
	   * @param stream to read the bytes from
	   * @return bytes read from the stream
	   * @exception java.io.IOException reports IO failures
	   *//*
	  public static byte[] loadBytesFromStream( java.io.InputStream in ) throws java.io.IOException {
	    return loadBytesFromStream( in, DEFAULT_CHUNK_SIZE );
	  }

	  *//**
	   * Loads bytes from the given input stream until the end of stream
	   * is reached.  Bytes are read in at the supplied <code>chunkSize</code>
	   * rate.
	   *
	   * @param stream to read the bytes from
	   * @return bytes read from the stream
	   * @exception java.io.IOException reports IO failures
	   *//*
	  public static byte[] loadBytesFromStream( java.io.InputStream in, int chunkSize )
	      throws java.io.IOException {
	    if( chunkSize < 1 )
	      chunkSize = DEFAULT_CHUNK_SIZE;

	    int count;
	    java.io.ByteArrayOutputStream bo = new java.io.ByteArrayOutputStream();
	    byte[] b = new byte[chunkSize];
	    try {
	      while( ( count = in.read( b, 0, chunkSize ) ) > 0 ) {
	        bo.write( b, 0, count );
	      }
	      byte[] thebytes = bo.toByteArray();
	      return thebytes;
	    }
	    finally {
	      bo.close();
	    }
	  }*/
	
	//public static Byte DEFAULT_BYTE = new Byte((byte) 0);
	
}
