package io.manasobi.utils

import org.apache.commons.exec.CommandLine

import spock.lang.Shared
import spock.lang.Specification

class CmdUtilsTest extends Specification {
	
	@Shared int SUCCESS_CODE = 0
	@Shared int FAIL_CODE = 1
	
	def "execute() :: String 파라미터"() {
		
		setup:
			int result
		
		when:
			result = CmdUtils.execute('ping localhost')
		then:
			result == SUCCESS_CODE	
			
		when:
			result = CmdUtils.execute('ping', 'localhost')
		then:
			result == SUCCESS_CODE
	}
	
	def "execute() :: String 배열 파라미터"() {
		
		expect:
			CmdUtils.execute(line, args) == result
		where:
			line   | args 							   || result
			'ping' | ['localhost'] as String[] 		   || SUCCESS_CODE
			'ping' | ['/XXX', 'localhost'] as String[] || FAIL_CODE
	}

	def "execute() :: Commandline 사용"() {
		
		setup:
			String line
			CommandLine commandLine
			String args
			int result
			
		when:
			line = 'ping localhost'
			commandLine = CommandLine.parse(line)
			result = CmdUtils.execute(commandLine)
		then:
			result == SUCCESS_CODE
			
		when:
			line = 'ping'
			commandLine = CommandLine.parse(line)
			args = "localhost"
			result = CmdUtils.execute(commandLine, args)
		then:
			result == SUCCESS_CODE

		when:
			line = 'XXX'
			commandLine = CommandLine.parse(line)
			result = CmdUtils.execute(commandLine, '')
		then:
			result != SUCCESS_CODE
			
	}
	
	def "execute() :: Timeout 파라미터 사용"() {
		
		setup:
			String line = "ping";
			CommandLine commandLine = CommandLine.parse(line)
			
		expect:
			CmdUtils.execute(commandLine, args, timeout) == status
		where:
			args		| timeout || status
			'localhost'	| 10 	  || FAIL_CODE
			'localhost'	| 0	  	  || SUCCESS_CODE
			'XXX'		| 10	  || FAIL_CODE
			''			| 10	  || FAIL_CODE
			null		| 10  	  || FAIL_CODE
	}
	
}
