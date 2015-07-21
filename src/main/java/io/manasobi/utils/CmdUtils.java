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

import java.io.ByteArrayOutputStream;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.lang3.StringUtils;

import io.manasobi.exception.CmdUtilsException;

/**
 * 「Apache Commons Exec」이용하여 Java 프로그램으로부터 외부 프로세스를 기동하는 유틸리티 클래스
 * 
 * @author manasobi
 * @since 1.0.0
 */

public class CmdUtils {
	
	private static final int SUCCESS_RETURN_CODE = 0;
	
	private static final int DEFAULT_TIMEOUT = 60 * 1000;
	
	/**
	 * 명령어에 의한 외부 프로세스의 기동
	 * 
	 * @param line 외부 프로세스 실행 명령어
	 * @return int 타입의 실행결과
	 */
	public static int execute(String line) {
		
		CommandLine commandLine = CommandLine.parse(line);
		
		return execute(commandLine, null);
	}

	/**
	 * 명령어 및 실행 파라미터에 의한 외부 프로세스의 기동
	 * 
	 * @param line     외부 프로세스 실행 명령어
	 * @param argument 명령실행 파라미터
	 * @return int 타입의 실행결과
	 */
	public static int execute(String line, String argument) {
		
		CommandLine commandLine = CommandLine.parse(line);
		
		return execute(commandLine, argument);
	}

	/**
	 * 명령어 및 실행 복수 파라미터에 의한 외부 프로세스의 기동
	 * 
	 * @param line      외부 프로세스 실행 명령어
	 * @param arguments 명령실행 파라미터 복수형
	 * @return int 타입의 실행결과
	 */
	public static int execute(String line, String[] arguments) {
		
		CommandLine commandLine = CommandLine.parse(line);
		
		for (String argument : arguments) {
			commandLine.addArgument(argument, false);
		}
		
		return execute(commandLine, null);
	}

	/**
	 * 명령어(commandLine)에 의한 외부 프로세스의 기동
	 * 
	 * @param commandLine 외부 프로세스 실행 명령어
	 * @return int 타입의 실행결과
	 */
	public static int execute(CommandLine commandLine) {

		return execute(commandLine, null, DEFAULT_TIMEOUT);
	}

	/**
	 * 명령어(commandLine) 및 복수 실행 파라미터에 의한 외부 프로세스의 기동
	 * 
	 * @param commandLine 외부 프로세스 실행 명령어
	 * @param argument    명령실행 파라미터
	 * @return int 타입의 실행결과
	 */
	public static int execute(CommandLine commandLine, String argument) {

		return execute(commandLine, argument, DEFAULT_TIMEOUT);
	}

	/**
	 * 제한시간 설정과 명령어(commandLine)에 의한 외부 프로세스의 기동
	 * 
	 * @param commandLine 외부 프로세스 실행 명령어
	 * @param timeout     시간제한설정
	 * @return int 타입의 실행결과
	 */
	public static int execute(CommandLine commandLine, long timeout) {
		
		return execute(commandLine, null, timeout);
	}

	/**
	 * 제한시간 설정과 명령어(commandLine) 및 복수 실행 파라미터에 의한 외부 프로세스의 기동
	 * 
	 * @param commandLine 외부 프로세스 실행 명령어
	 * @param argument    명령실행 파라미터
	 * @param timeout     시간제한설정
	 * 
	 * @return int 타입의 실행결과
	 */
	public static int execute(CommandLine commandLine, String argument, long timeout) {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();		
		PumpStreamHandler streamHandler = new PumpStreamHandler(baos);
		
		DefaultExecutor executor = new DefaultExecutor();
		executor.setStreamHandler(streamHandler);

		// 파라미터가 필요할경우
		if (StringUtils.isNotEmpty(argument)) {
			commandLine.addArguments(argument);
		}

		// 타임아웃시간 설정
		if (timeout > 0) {
			ExecuteWatchdog watchdog = new ExecuteWatchdog(timeout);
			executor.setWatchdog(watchdog);
		}
		
		// 정상종료의 경우 반환되는 값
		executor.setExitValue(SUCCESS_RETURN_CODE);
		
		try {
			
			DefaultExecuteResultHandler resultHandler  = new DefaultExecuteResultHandler();	

			executor.execute(commandLine, resultHandler);
			resultHandler.waitFor();
			
			return resultHandler.getExitValue();
			
		} catch (Exception e) {
			
			throw new CmdUtilsException(e.getMessage());
		}
	}

}