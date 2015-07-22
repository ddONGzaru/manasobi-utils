package io.manasobi.utils

import java.util.concurrent.TimeUnit

import org.springframework.util.StopWatch

import spock.lang.Specification

class ThreadUtilsTest extends Specification {

	def "sleep()"() {
		
		setup:
			StopWatch stopWatch = new StopWatch()
			int sleepTime = 1000
		
		when:
			stopWatch.start()
			ThreadUtils.sleep(1, TimeUnit.SECONDS)
			stopWatch.stop()
		then:
			sleepTime <= stopWatch.totalTimeMillis 	
	}
}
