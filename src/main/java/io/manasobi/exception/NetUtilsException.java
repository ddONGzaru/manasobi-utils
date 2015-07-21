package io.manasobi.exception;

/**
 * NetUtils 클래스에서 발생한 예외 처리를 담당.
 * 
 * @author manasobi
 * @since 1.0.0
 *
 */
public class NetUtilsException extends RuntimeException {

	private static final long serialVersionUID = 4119855216683025602L;

	public NetUtilsException(String msg) {
		super(msg);
	}

}
