package io.manasobi.utils;

import java.util.concurrent.TimeUnit;

import io.manasobi.exception.ThreadUtilsException;

/**
 * 대기시간(sleep)을 지정하기 위해서 사용되는 Thread.sleep은 uncheckedException이다.<br/>
 * 불필요한 try-catch를 생략하기위해 runtimeException으로 감싸는 wrapper 클래스를 제공한다.
 * 
 * @author manasobi
 * @since 1.0.0
 */
public final class ThreadUtils {
	
	private ThreadUtils() { }
	
	public static void sleep(long time, TimeUnit timeUnit) {
		
		long sleepTime = TimeUnit.MILLISECONDS.convert(time, timeUnit);
		
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			throw new ThreadUtilsException(e.getMessage());
		}
	}
}
