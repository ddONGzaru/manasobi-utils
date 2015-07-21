package io.manasobi.exception;

/**
 * ThreadUtils 클래스에서 발생한 예외 처리를 담당.
 * 
 * @author manasobi
 * @since 1.0.0
 *
 */
public class ThreadUtilsException extends RuntimeException {

	private static final long serialVersionUID = -2042138244118076621L;

	public ThreadUtilsException(String msg) {
		super(msg);
	}

}
