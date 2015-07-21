/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.manasobi.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Selector;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.LineIterator;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.util.FileCopyUtils;

import io.manasobi.constnat.Result;
import io.manasobi.exception.IOUtilsException;

/**
 * 스트림과 리더로 부터 String과 Byte Array를 생성하고, 안전하게 스트림을 닫는 기능을 제공한다.
 * 
 * @author manasobi
 * @since 1.0.0
 */
public final class IOUtils {
	
	private IOUtils() { }

	private static Result buildFailResult(Result result, String errMsg) {
		
		result = Result.FAIL;
		result.setMessage(errMsg);
		
		return result;
	}

	/**
	 * Closeable 를 무조건 닫는다.
	 * 
	 * @param closeable 닫을 대상의 Closeable, null 이거나 이미 닫혀 있을수도 있다.
	 */
	public static void closeQuietly(Closeable closeable) {
		org.apache.commons.io.IOUtils.closeQuietly(closeable);
	}

	/**
	 * InputStream 를 무조건 닫는다.
	 * 
	 * @param is 닫을 대상의 InputStream, null 이거나 이미 닫혀 있을수도 있다.
	 */
	public static void closeQuietly(InputStream is) {
		org.apache.commons.io.IOUtils.closeQuietly(is);
	}

	/**
	 * OutputStream 를 무조건 닫는다.
	 * 
	 * @param os 닫을 대상의 OutputStream, null 이거나 이미 닫혀 있을수도 있다.
	 */
	public static void closeQuietly(OutputStream os) {
		org.apache.commons.io.IOUtils.closeQuietly(os);
	}

	/**
	 * Reader 를 무조건 닫는다.
	 * 
	 * @param reader 닫을 대상의 Reader, null 이거나 이미 닫혀 있을수도 있다.
	 */
	public static void closeQuietly(Reader reader) {
		org.apache.commons.io.IOUtils.closeQuietly(reader);
	}

	/**
	 * Selector 를 무조건 닫는다.
	 * 
	 * @param selector 닫을 대상의 Selector, null 이거나 이미 닫혀 있을수도 있다.
	 */
	public static void closeQuietly(Selector selector) {
		org.apache.commons.io.IOUtils.closeQuietly(selector);
	}

	/**
	 * ServerSocket 를 무조건 닫는다.
	 * 
	 * @param sock 닫을 대상의 ServerSocket, null 이거나 이미 닫혀 있을수도 있다.
	 */
	public static void closeQuietly(ServerSocket sock) {
		org.apache.commons.io.IOUtils.closeQuietly(sock);
	}

	/**
	 * Socket 를 무조건 닫는다.
	 * 
	 * @param sock 닫을 대상의 Socket, null 이거나 이미 닫혀 있을수도 있다.
	 */
	public static void closeQuietly(Socket sock) {
		org.apache.commons.io.IOUtils.closeQuietly(sock);
	}

	/**
	 * Writer 를 무조건 닫는다.
	 * 
	 * @param output 닫을 대상의 Writer, null 이거나 이미 닫혀 있을수도 있다.
	 */
	public static void closeQuietly(Writer output) {
		org.apache.commons.io.IOUtils.closeQuietly(output);
	}

	/**
	 * InputStream 에서 bytes를 OutputStream 에 복사한다.
	 * 
	 * @param is InputStream 를 읽는다.
	 * @param os OutputStream 에 쓴다.
	 * @return 복사한 bytes 수
	 */
	public static int copy(InputStream is, OutputStream os) throws IOUtilsException {
		
		try {
			return org.apache.commons.io.IOUtils.copy(is, os);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}
	
	/**
	 * Writer에 InputStream의 문자를 플랫폼의 기본 문자 인코딩을 사용하여 바이트 복사를 한다.
	 * 
	 * @param is     InputStream에서 읽는다
	 * @param writer Writer 에 복사한다
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result copy(InputStream is, Writer writer) {
		
		try {
			
			org.apache.commons.io.IOUtils.copy(is, writer);
			
			return Result.SUCCESS;
			
		} catch (Exception e) {
			
			Result error = Result.FAIL;
			error.setMessage(e.getMessage());
			
			return error;
		}
	}

	/**
	 * OutputStream에 Reader 의 문자를 기본 인코딩 방식을 사용하여 복사하고, flush 처리 한다.
	 * 
	 * @param reader Reader 에서 읽는다
	 * @param os     OutputStream 에 복사한다
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result copy(Reader reader, OutputStream os) {
		
		try {
			
			org.apache.commons.io.IOUtils.copy(reader, os);
			
			return Result.SUCCESS;
			
		} catch (Exception e) {
			
			Result error = Result.FAIL;
			error.setMessage(e.getMessage());
			
			return error;
		}		
	}

	/**
	 * OutputStream에 InputStream 의 문자를 지정된 인코딩 방식을 사용하여 복사하고, flush 처리 한다.
	 * 
	 * @param is InputStream 에서 읽는다
	 * @param os OutputStream 에 복사한다
	 * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result copy(InputStream is, OutputStream os, String encoding) {
        
        try {
        	BufferedReader in = new BufferedReader(new InputStreamReader(is));
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(os, encoding));
			
			FileCopyUtils.copy(in, out);
			
		} catch (Exception e) {
			
			Result error = Result.FAIL;
			error.setMessage(e.getMessage());
			
			return error;
		}
        
        return Result.SUCCESS;
	}
	
	/**
	 * OutputStream에 Reader 의 문자를 지정된 인코딩 방식을 사용하여 복사하고, flush 처리 한다.
	 * 
	 * @param reader Reader 에서 읽는다
	 * @param os     OutputStream 에 복사한다
	 * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result copy(Reader reader, OutputStream os, String encoding) {
        
		Result result = Result.EMPTY;
		
        try {
        	
        	BufferedReader in = new BufferedReader(reader);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(os, encoding));
			
			FileCopyUtils.copy(in, out);
			
		} catch (Exception e) {
			
			return buildFailResult(result, e.getMessage());
		}
        
        return Result.SUCCESS;
	}

	/**
	 * Writer에 Reader의 문자를 복사 한다. 
	 *  
	 * @param reader InputStream에서 읽는다
	 * @param writer Writer 에 복사한다
	 * @return 복사한 bytes 수
	 */
	public static int copy(Reader reader, Writer writer) {
		
		try {
			return org.apache.commons.io.IOUtils.copy(reader, writer);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * 큰 InputStream (2GB 이상)에서 OutputStream으로 bytes를 복사한다.
	 * 
	 * @param is InputStream에서 읽는다
	 * @param os OutputStream 에 쓴다.
	 * @return 복사한 bytes 수
	 */
	public static long copyLarge(InputStream is, OutputStream os) {
		
		try {
			return org.apache.commons.io.IOUtils.copyLarge(is, os);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * 큰 InputStream (2GB 이상)에서 OutputStream으로 bytes를 복사한다.
	 * 
	 * @param is InputStream에서 읽는다
	 * @param os OutputStream 에 쓴다.
	 * @param buffer 복사시 사용하는 buffer
	 * @return 복사한 bytes 수
	 */
	public static long copyLarge(InputStream is, OutputStream os, byte[] buffer) {
		
		try {
			return org.apache.commons.io.IOUtils.copyLarge(is, os, buffer);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * 큰 InputStream (2GB 이상)에서 OutputStream으로 bytes를 복사한다.
	 * 
	 * @param is InputStream에서 읽는다
	 * @param os OutputStream 에 쓴다.
	 * @param inputOffset 시작을 bytes 수 만큼 건너 뛰고 복사를 시작한다.
	 * @param length 복사할 bytes 수
	 * @return 복사한 bytes 수
	 */
	public static long copyLarge(InputStream is, OutputStream os, long inputOffset, long length) {
		
		try {
			return org.apache.commons.io.IOUtils.copyLarge(is, os, inputOffset, length);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * 큰 InputStream (2GB 이상)에서 OutputStream으로 bytes를 복사한다.
	 * 
	 * @param is InputStream에서 읽는다
	 * @param os OutputStream 에 복사한다
	 * @param inputOffset bytes 수 만큼 건너 뛰고 복사를 시작한다.
	 * @param length      복사할 bytes 수
	 * @param buffer      버퍼를 사용하여 복사한다.
	 * @return 복사한 bytes 수
	 */
	public static long copyLarge(InputStream is, OutputStream os, final long inputOffset, final long length, byte[] buffer) {
		
		try {
			return org.apache.commons.io.IOUtils.copyLarge(is, os, inputOffset, length, buffer);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * Writer에 큰 사이즈 Reader(2GB 이상)의 문자를 복사 한다.
	 * 
	 * @param reader  Reader 에서 읽는다
	 * @param writer  Writer 에 복사한다
	 * @return 복사한 char 수
	 */
	public static long copyLarge(Reader reader, Writer writer) {
		
		try {
			return org.apache.commons.io.IOUtils.copyLarge(reader, writer);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * Writer에 큰 사이즈 Reader(2GB 이상)의 문자를 복사 한다.
	 * 
	 * @param reader Reader 에서 읽는다
	 * @param writer Writer 에 복사한다
	 * @param buffer 버퍼를 사용하여 복사한다
	 * @return 복사한 char 수
	 */
	public static long copyLarge(Reader reader, Writer writer, char[] buffer) {
		
		try {
			return org.apache.commons.io.IOUtils.copyLarge(reader, writer, buffer);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * Writer에 큰 사이즈 Reader(2GB 이상)의 문자를 복사 한다.
	 * 
	 * @param reader Reader 에서 읽는다
	 * @param writer Writer 에 복사한다
	 * @param inputOffset bytes 수 만큼 건너 뛰고 복사를 시작한다.
	 * @param length 복사 할 bytes 수
	 * @return 복사한 char 수
	 */
	public static long copyLarge(Reader reader, Writer writer, final long inputOffset, final long length) {
		
		try {
			return org.apache.commons.io.IOUtils.copyLarge(reader, writer, inputOffset, length);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * Writer에 큰 사이즈 Reader(2GB 이상)의 문자를 복사 한다.
	 * 
	 * @param reader Reader 에서 읽는다
	 * @param writer Writer 에 복사한다
	 * @param inputOffset bytes 수 만큼 건너 뛰고 복사를 시작한다.
	 * @param length      복사 할 bytes 수
	 * @param buffer      buffer를 사용하여 복사한다
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static long copyLarge(Reader reader, Writer writer, final long inputOffset, final long length, char[] buffer) {
		
		try {
			return org.apache.commons.io.IOUtils.copyLarge(reader, writer, inputOffset, length, buffer);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * InputStream 의 내용을 지정된 문자 인코딩 방식으로 인코딩 후 LineIterator 로 반환한다.
	 * 
	 * @param is 내용을 가져올 InputStream
	 * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
	 * @return 스트링으로 구성된 List
	 */
	public static LineIterator lineIterator(InputStream is, String encoding) {
		
		try {
			return org.apache.commons.io.IOUtils.lineIterator(is, encoding);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * Reader 을 LineIterator 로 반환한다.
	 * 
	 * @param reader 내용을 가져올 Reader
	 * @return 스트링으로 구성된 List
	 */
	public static LineIterator lineIterator(Reader reader) {

		try {
			return org.apache.commons.io.IOUtils.lineIterator(reader);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
		
	}
		
	/**
	 * InputStream 로부터 문자를 읽는다.
	 * 
	 * @param is 읽을 대상의 InputStream
	 * @param buffer 입력 대상 버퍼
	 * @return 실제 읽은 길이, EOF에 도달 할 경우에 요청된 길이보다 적을 수 있다.
	 */
	public static int read(InputStream is, byte[] buffer) {
		
		if (is == null) {
			throw new IOUtilsException("InputStream이 null입니다.");
		}
		
		try {
			return org.apache.commons.io.IOUtils.read(is, buffer);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * InputStream 에서 요청한 바이트 수 만큼 읽는다.
	 * 
	 * @param is     읽을 대상의 InputStream
	 * @param buffer 입력 대상 버퍼
	 * @param offset 읽을 바이트가 시작되는 오프셋
	 * @param length 읽을 수 있는 바이트 길이, 0보다 커야한다.
	 * @return 실제 읽은 길이, EOF에 도달 할 경우에 요청된 길이보다 적을 수 있다.
	 */
	public static int read(InputStream is, byte[] buffer, int offset, int length) {
		
		if (is == null) {
			throw new IOUtilsException("InputStream이 null입니다.");
		}
		
		try {
			return org.apache.commons.io.IOUtils.read(is, buffer, offset, length);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * Reader 로부터 문자를 읽는다.
	 * 
	 * @param reader 읽을 대상의 Reader
	 * @param buffer 입력 대상 버퍼
	 * @return 실제 읽은 길이, EOF에 도달 할 경우에 요청된 길이보다 적을 수 있다.
	 */
	public static int read(Reader reader, char[] buffer) {
		
		if (reader == null) {
			throw new IOUtilsException("Reader가 null입니다.");
		}
		
		try {
			return org.apache.commons.io.IOUtils.read(reader, buffer);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * Reader 에서 요청한 문자를 수 만큼 읽는다
	 * 
	 * @param reader 읽을 대상의 Reader
	 * @param buffer 입력 대상 버퍼
	 * @param offset 읽을 문자가 시작되는 오프셋
	 * @param length 읽을 수 있는 문자 길이, 0보다 커야한다.
	 * @return 실제 읽은 길이, EOF에 도달 할 경우에 요청된 길이보다 적을 수 있다.
	 */
	public static int read(Reader reader, char[] buffer, int offset, int length) {
		
		if (reader == null) {
			throw new IOUtilsException("Reader가 null입니다.");
		}
		
		try {
			return org.apache.commons.io.IOUtils.read(reader, buffer, offset, length);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * InputStream 에서 요청한 byte 수 만큼 읽는다. byte 길이가 실패값 일 경우 Fail 반환.
	 * 
	 * @param is     읽을 대상의 InputStream
	 * @param buffer 입력 대상 buffer
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result readFully(InputStream is, byte[] buffer) {
		
		if (is == null) {
			throw new IOUtilsException("InputStream이 null입니다.");
		}
		
		try {
			org.apache.commons.io.IOUtils.readFully(is, buffer);
			return Result.SUCCESS;
		} catch (IOException e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * InputStream 에서 요청한 byte 수 만큼 읽는다. byte 길이가 실패값 일 경우 Fail 반환.
	 * 
	 * @param is     읽을 대상의 InputStream
	 * @param buffer 입력 대상 버퍼
	 * @param offset 읽을 byte가 시작되는 오프셋
	 * @param length 읽을 수 있는 byte 길이, 0보다 커야한다.
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result readFully(InputStream is, byte[] buffer, int offset, int length) {
		
		if (is == null) {
			return buildFailResult(Result.FAIL, "InputStream이 null입니다.");
		}
		
		try {
			org.apache.commons.io.IOUtils.readFully(is, buffer, offset, length);
			return Result.SUCCESS;
		} catch (Exception e) {
			return buildFailResult(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * Reader 에서 요청한 문자 수 만큼 읽는다. 문자 길이가 실패값 일 경우 Fail 반환.
	 * 
	 * @param reader 읽을 대상의 Reader
	 * @param buffer 입력 대상 버퍼
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result readFully(Reader reader, char[] buffer) {
		
		if (reader == null) {
			return buildFailResult(Result.FAIL, "Reader가 null입니다.");
		}
		
		try {
			org.apache.commons.io.IOUtils.readFully(reader, buffer);
			return Result.SUCCESS;
		} catch (IOException e) {
			return buildFailResult(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * Reader 에서 요청한 문자 수 만큼 읽는다. 문자 길이가 실패값 일 경우 Fail 반환.
	 * 
	 * @param reader 읽을 대상의 Reader
	 * @param buffer 입력 대상 버퍼
	 * @param offset 읽을 문자가 시작되는 오프셋
	 * @param length 읽을 수 있는 문자 길이, 0보다 커야한다.
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result readFully(Reader reader, char[] buffer, int offset, int length) {
		
		if (reader == null) {			
			return buildFailResult(Result.FAIL, "Reader가 null입니다.");
		}
		
		try {
			org.apache.commons.io.IOUtils.readFully(reader, buffer, offset, length);
			return Result.SUCCESS;
		} catch (Exception e) {
			return buildFailResult(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * InputStream의 내용을 기본 문자 인코딩 방식을 사용하여 한줄씩 읽고 List<String>로 반환한다.
	 * 
	 * @param is 내용을 가져올 InputStream
	 * @return 스트링으로 구성된 List
	 */
	public static List<String> readLines(InputStream is) {
		
		try {
			return org.apache.commons.io.IOUtils.readLines(is);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}		
	}

	/**
	 * InputStream의 내용을 지정된 문자 인코딩 방식을 사용하여 한줄씩 읽고 List<String>로 반환한다.
	 * 
	 * @param is 내용을 가져올 InputStream
	 * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
	 * @return 스트링으로 구성된 List
	 */
	public static List<String> readLines(InputStream is, String encoding) {
		
		try {
			return org.apache.commons.io.IOUtils.readLines(is, encoding);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * Reader 의 내용을 지정 문자 인코딩을 사용하여 한줄씩 읽고 List<String>로 반환한다.
	 * 
	 * @param reader 내용을 가져올 InputStream
	 * @return 스트링으로 구성된 List
	 */
	public static List<String> readLines(Reader reader) {
		
		try {
			return org.apache.commons.io.IOUtils.readLines(reader);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * InputStream에서 실제로 Skip 할 바이트 수를 계산하여 반환한다.
	 * 
	 * @param is
	 *            skip 할 대상
	 * @param toSkip
	 *            건너 뛸 바이트의 수
	 * @return 실제로 건너 뛸 바이트의 수
	 */
	public static long skip(InputStream is, long toSkip) {
		
		if (is == null) {
			throw new IOUtilsException("InputStream이 null입니다.");
		}
		
		try {
			return org.apache.commons.io.IOUtils.skip(is, toSkip);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * Reader에서 실제로 Skip 할 문자 수를 계산하여 반환한다.
	 * 
	 * @param reader skip 할 대상
	 * @param toSkip 건너 뛸 바이트의 수
	 * @return 실제로 건너 뛸 바이트의 수
	 */
	public static long skip(Reader reader, long toSkip) {
		
		if (reader == null) {
			throw new IOUtilsException("Reader가 null입니다.");
		}
		
		try {
			return org.apache.commons.io.IOUtils.skip(reader, toSkip);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * InputStream 에서 Skip 할 바이트 수가 실패값 일 경우 Fail 반환.
	 * 
	 * @param is Skip 할 대상
	 * @param toSkip Skip 할 바이트의 수
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result skipFully(InputStream is, long toSkip) {
		
		if (is == null) {
			return buildFailResult(Result.FAIL, "InputStream이 null입니다.");
		}
		
		try {
			org.apache.commons.io.IOUtils.skipFully(is, toSkip);
			return Result.SUCCESS;
		} catch (Exception e) {
			return buildFailResult(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * Reader 에서 Skip 할 문자 수가 실패값 일 경우 Fail 반환.
	 * 
	 * @param reader Skip 할 대상
	 * @param toSkip Skip 할 바이트의 수
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result skipFully(Reader reader, long toSkip) {
		
		if (reader == null) {
			return buildFailResult(Result.FAIL, "Reader가 null입니다.");
		}
		
		try {
			org.apache.commons.io.IOUtils.skipFully(reader, toSkip);
			return Result.SUCCESS;
		} catch (Exception e) {
			return buildFailResult(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * InputStream 를 BufferedInputStream으로 변환한다.<br />
	 * 
	 * @param is InputStream
	 * @return 완충된 InputStream, 예외시 <code>null</code>
	 */
	public static InputStream toBufferedInputStream(InputStream is) {

		if (is == null) {
			throw new IOUtilsException("InputStream이 null입니다.");
		}
		
		try {
			return ByteArrayOutputStream.toBufferedInputStream(is);
		} catch (IOException e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * 주어진 Reader의 객체가 BufferedReader인가를 확인후 BufferedReader를 반환한다.<br />
	 * 
	 * @param reader Reader
	 * @return 주어진 reader 또는 새로운 BufferedReader
	 */
	public static BufferedReader toBufferedReader(Reader reader) {
		
		if (reader == null) {
			throw new IOUtilsException("Reader가 null입니다.");
		}
		
		return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
	}

	/**
	 * InputStream 내용을 읽어 byte[]로 반환한다.
	 * 
	 * @param is 내용을 가져올 InputStream
	 * @return 요청한 바이트 배열
	 */
	public static byte[] toByteArray(InputStream is) {
		
		try {
			return org.apache.commons.io.IOUtils.toByteArray(is);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * byte[]로 InputStream 내용을 읽어 byte[]로 반환한다. InputStream의 길이가 알려져 있을 때 사용한다.
	 * 길이(int)를 0보다 작거나 같은지 검사한다.
	 * 
	 * @param is   내용을 가져올 InputStream
	 * @param size InputStream 의 사이즈
	 * @return 요청한 바이트 배열
	 */
	public static byte[] toByteArray(InputStream is, int size) {
		
		try {
			return org.apache.commons.io.IOUtils.toByteArray(is, size);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * InputStream 내용을 읽어 byte[]로 반환한다. InputStream의 길이가 알려져 있을 때 사용한다.
	 * 길이(long)를 int로 캐스팅 될 수 있는지 검사한다.
	 * 
	 * @param is   내용을 가져올 InputStream
	 * @param size InputStream 의 사이즈
	 * @return 요청한 바이트 배열
	 */
	public static byte[] toByteArray(InputStream is, long size) {
		
		try {
			return org.apache.commons.io.IOUtils.toByteArray(is, size);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * Reader 내용을 기본 문자 인코딩을 사용하여 byte[]로 반환한다.
	 * 
	 * @param reader 내용을 가져올 Reader
	 * @return 요청한 바이트 배열
	 */
	public static byte[] toByteArray(Reader reader) {
		
		try {
			return org.apache.commons.io.IOUtils.toByteArray(reader);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * Reader 내용을 지정된 문자 인코딩을 사용하여 byte[]로 반환한다.
	 * 
	 * @param reader   내용을 가져올 Reader
	 * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
	 * @return 요청한 바이트 배열
	 */
	public static byte[] toByteArray(Reader reader, String encoding) {
		
		try {
			return org.apache.commons.io.IOUtils.toByteArray(reader, encoding);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}	
	}

	/**
	 * InputStream 내용을 기본 문자 인코딩을 사용하여 char[]로 내용을 읽어 byte[]로 반환한다.
	 * 
	 * @param is 내용을 가져올 InputStream
	 * @return 요청한 char 배열
	 */
	public static char[] toCharArray(InputStream is) {
		
		try {
			return org.apache.commons.io.IOUtils.toCharArray(is);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * InputStream 내용을 지정된 문자 인코딩을 사용하여 char[]로 반환한다.
	 * 
	 * @param is       내용을 가져올 InputStream
	 * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
	 * @return 요청한 char 배열
	 */
	public static char[] toCharArray(InputStream is, String encoding) {
		
		try {
			return org.apache.commons.io.IOUtils.toCharArray(is);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * Reader 내용을 읽어 char[]로 반환한다.
	 * 
	 * @param reader 내용을 가져올 Reader
	 * @return 요청한 char 배열
	 */
	public static char[] toCharArray(Reader reader) {
		
		try {
			return org.apache.commons.io.IOUtils.toCharArray(reader);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * CharSequence의 바이트를 기본 인코딩 방식을 사용하여 입력 스트림으로 변환한다.
	 * 
	 * @param input 내용을 가져올 CharSequence
	 * @return 변환한 입력 스트림
	 */
	public static InputStream toInputStream(CharSequence input) {
		
		if (input == null) {
			throw new IOUtilsException("CharSequence가 null입니다.");
		}
		return org.apache.commons.io.IOUtils.toInputStream(input);
	}

	/**
	 * CharSequence의 바이트를 지정된 인코딩 방식을 사용하여 입력 스트림으로 변환한다.
	 * 
	 * @param input 내용을 가져올 CharSequence
	 * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
	 * @return 변환한 입력 스트림
	 */
	public static InputStream toInputStream(CharSequence input, String encoding) {
		
		if (input == null) {
			throw new IOUtilsException("CharSequence가 null입니다.");
		}
		
		try {
			return org.apache.commons.io.IOUtils.toInputStream(input, encoding);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * 문자열의 바이트를 기본 인코딩 방식을 사용하여 입력 스트림으로 변환한다.
	 * 
	 * @param input 내용을 변환할 String
	 * @return 변환한 입력 스트림
	 */
	public static InputStream toInputStream(String input) {
		
		if (StringUtils.isEmpty(input)) {
			throw new IOUtilsException("String이 null입니다.");
		}
		
		return org.apache.commons.io.IOUtils.toInputStream(input);
	}

	/**
	 * 문자열의 바이트를 지정한 인코딩 방식을 사용하여 입력 스트림으로 변환한다.
	 * 
	 * @param input    내용을 변환할 String
	 * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
	 * @return 변환한 입력 스트림
	 */
	public static InputStream toInputStream(String input, String encoding) {
		
		if (StringUtils.isEmpty(input)) {
			throw new IOUtilsException("String이 null입니다.");
		}
		
		try {
			return org.apache.commons.io.IOUtils.toInputStream(input, encoding);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * InputStream 내용을 기본 문자 인코딩을 사용하여 String으로 반환한다.
	 * 
	 * @param is 내용을 가져올 InputStream
	 * @return 요청한 String
	 */
	public static String toString(InputStream is) {
		
		try {
			return org.apache.commons.io.IOUtils.toString(is);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}	
	}

	/**
	 * InputStream 내용을 지정한 문자 인코딩을 사용하여 String으로 반환한다.
	 * 
	 * @param is       내용을 가져올 InputStream
	 * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
	 * @return 요청한 String
	 */
	public static String toString(InputStream is, String encoding) {
		
		try {
			return org.apache.commons.io.IOUtils.toString(is, encoding);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * Reader 내용을 읽어 String으로 반환한다.
	 * 
	 * @param reader 내용을 가져올 Reader
	 * @return 요청한 String
	 */
	public static String toString(Reader reader) {
		
		try {
			return org.apache.commons.io.IOUtils.toString(reader);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * 지정된 URI에 있는 내용을 읽어 반환한다.
	 * 
	 * @param uri URI 주소
	 * @return URL의 내용
	 */
	public static String toString(URI uri) {
		
		try {
			return org.apache.commons.io.IOUtils.toString(uri);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * 지정된 URI에 있는 내용을 읽어 지정한 문자 인코딩 방식으로 인코딩 후 반환한다.
	 * 
	 * @param uri URI 주소
	 * @param encoding url 내용을 encoding 할 문자 인코딩 방식
	 * @return URL의 내용
	 */
	public static String toString(URI uri, String encoding) {
		
		try {
			return org.apache.commons.io.IOUtils.toString(uri, encoding);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * 지정된 URL에있는 내용을 String으로 반환한다.
	 * 
	 * @param url URL 주소
	 * @return URL의 내용
	 */
	public static String toString(URL url) {
		
		try {
			return org.apache.commons.io.IOUtils.toString(url);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * 지정된 URL에있는 내용을 지정한 문자 인코딩을 사용하여 인코딩 후 반환한다.
	 * 
	 * @param url URL 주소
	 * @param encoding URL 내용을 encoding 할 문자 인코딩 방식
	 * @return URL의 내용
	 */
	public static String toString(URL url, String encoding) {
		
		try {
			return org.apache.commons.io.IOUtils.toString(url);
		} catch (Exception e) {
			throw new IOUtilsException(e.getMessage());
		}
	}

	/**
	 * byte[]에서 OutputStream 으로 바이트를 쓴다.
	 * 
	 * @param data   byte array 를 읽는다, null 은 무시
	 * @param output OutputStream 에 쓴다.
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result write(byte[] data, OutputStream output) {
		
		try {
			org.apache.commons.io.IOUtils.write(data, output);
			return Result.SUCCESS;
		} catch (Exception e) {
			return buildFailResult(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * byte[]에서 Writer 로 바이트를 쓴다.
	 * 
	 * @param data   byte array 를 읽는다, null 은 무시
	 * @param output Writer 에 쓴다.
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result write(byte[] data, Writer output) {
		
		try {
			org.apache.commons.io.IOUtils.write(data, output);
			return Result.SUCCESS;
		} catch (Exception e) {
			return buildFailResult(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * byte[]에서 Writer 로 바이트를 쓴다.
	 * 
	 * @param data   byte array 를 읽는다, null 은 무시
	 * @param output Writer 에 쓴다.
	 * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result write(byte[] data, Writer output, String encoding) {
		
		try {
			org.apache.commons.io.IOUtils.write(data, output, encoding);
			return Result.SUCCESS;
		} catch (Exception e) {
			return buildFailResult(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * chars에서 OutputStream 로 기본 인코딩 방식을 사용하여 쓴다.
	 * 
	 * @param data   byte array 를 읽는다, null 은 무시
	 * @param output OutputStream 에 쓴다.
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result write(char[] data, OutputStream output) {
		
		try {
			org.apache.commons.io.IOUtils.write(data, output);
			return Result.SUCCESS;
		} catch (Exception e) {
			return buildFailResult(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * chars에서 OutputStream 로 지정된 인코딩 방식을 사용하여 쓴다.
	 * 
	 * @param data   byte array 를 읽는다, null 은 무시
	 * @param output OutputStream 에 쓴다.
	 * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result write(char[] data, OutputStream output, String encoding) {
		
		try {
			org.apache.commons.io.IOUtils.write(data, output, encoding);
			return Result.SUCCESS;
		} catch (Exception e) {
			return buildFailResult(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * chars에서 Writer 로 기본 인코딩 방식을 사용하여 쓴다.
	 * 
	 * @param data   byte array 를 읽는다, null 은 무시
	 * @param output Writer 에 쓴다.
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result write(char[] data, Writer output) {
		
		try {
			org.apache.commons.io.IOUtils.write(data, output);
			return Result.SUCCESS;
		} catch (Exception e) {
			return buildFailResult(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * CharSequence에서 OutputStream 로 기본 인코딩 방식을 사용하여 chars를 쓴다.
	 * 
	 * @param data   chars 를 읽는다, null 은 무시
	 * @param output OutputStream 에 쓴다.
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result write(CharSequence data, OutputStream output) {
		
		try {
			org.apache.commons.io.IOUtils.write(data, output);
			return Result.SUCCESS;
		} catch (Exception e) {
			return buildFailResult(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * CharSequence에서 OutputStream 로 지정된 인코딩 방식을 사용하여 chars를 쓴다.
	 * 
	 * @param data   chars 를 읽는다, null 은 무시
	 * @param output OutputStream 에 쓴다.
	 * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result write(CharSequence data, OutputStream output,
			String encoding) {
		
		try {
			org.apache.commons.io.IOUtils.write(data, output, encoding);
			return Result.SUCCESS;
		} catch (Exception e) {
			return buildFailResult(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * CharSequence에서 Writer 로 chars를 쓴다.
	 * 
	 * @param data   chars 를 읽는다, null 은 무시
	 * @param output Writer 에 쓴다.
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result write(CharSequence data, Writer output) {
		
		try {
			org.apache.commons.io.IOUtils.write(data, output);
			return Result.SUCCESS;
		} catch (Exception e) {
			return buildFailResult(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * String에서 OutputStream 로 기본 인코딩 방식을 사용하여 문자를 쓴다.
	 * 
	 * @param data   String 를 읽는다, null 은 무시
	 * @param output OutputStream 에 쓴다.
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result write(String data, OutputStream output) {
		
		try {
			org.apache.commons.io.IOUtils.write(data, output);
			return Result.SUCCESS;
		} catch (Exception e) {
			return buildFailResult(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * String에서 OutputStream 로 지정된 인코딩 방식을 사용하여 문자를 쓴다.
	 * 
	 * @param data   String 를 읽는다, null 은 무시
	 * @param output OutputStream 에 쓴다.
	 * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result write(String data, OutputStream output, String encoding) {
		
		try {
			org.apache.commons.io.IOUtils.write(data, output, encoding);
			return Result.SUCCESS;
		} catch (Exception e) {
			return buildFailResult(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * String에서 Writer 로 문자를 쓴다.
	 * 
	 * @param data   String 를 읽는다, null 은 무시
	 * @param output Writer 에 쓴다.
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result write(String data, Writer output) {
		
		try {
			org.apache.commons.io.IOUtils.write(data, output);
			return Result.SUCCESS;
		} catch (Exception e) {
			return buildFailResult(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * Collection의 각 항목을 기본 인코딩 방식 및 지정된 라인 결말에 따라 OutputStream에 쓴다.
	 * 
	 * @param lines      Line을 쓴다. NULL 항목은 빈 라인을 생성
	 * @param lineEnding 라인 구분자, default : null
	 * @param os         OutputStream에 쓴다. null 이거나 닫을수 없다.
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result writeLines(Collection<?> lines, String lineEnding, OutputStream os) {
		
		try {
			org.apache.commons.io.IOUtils.writeLines(lines, lineEnding,	os);
			return Result.SUCCESS;
		} catch (Exception e) {
			return buildFailResult(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * Collection의 각 항목을 지정된 인코딩 방식 및 지정된 라인 결말에 따라 OutputStream에 쓴다.
	 * 
	 * @param lines      Line을 쓴다. NULL 항목은 빈 라인을 생성
	 * @param lineEnding 라인 구분자, default : null
	 * @param os OutputStream에 쓴다. null 이거나 닫을수 없다.
	 * @param encoding 사용할 문자 인코딩 방식, null 일 경우 기본 인코딩 방식 사용
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result writeLines(Collection<?> lines, String lineEnding,	OutputStream os, String encoding) {
		
		try {
			org.apache.commons.io.IOUtils.writeLines(lines, lineEnding,	os, encoding);
			return Result.SUCCESS;
		} catch (Exception e) {
			return buildFailResult(Result.FAIL, e.getMessage());
		}
	}

	/**
	 * Collection의 각 항목을 기본 인코딩 방식 및 지정된 라인 결말에 따라 Writer에 쓴다.
	 * 
	 * @param lines      Line을 쓴다. NULL 항목은 빈 라인을 생성
	 * @param lineEnding 라인 구분자, default : null
	 * @param writer     Writer에 쓴다. null 이거나 닫을수 없다.
	 * @return 성공하면 enum 타입의 Status.SUCCESS를 그렇지 않으면 Status.FAIL을 반환
	 */
	public static Result writeLines(Collection<?> lines, String lineEnding,	Writer writer) {
		
		try {
			org.apache.commons.io.IOUtils.writeLines(lines, lineEnding,	writer);
			return Result.SUCCESS;
		} catch (Exception e) {
			return buildFailResult(Result.FAIL, e.getMessage());
		}
	}

}
