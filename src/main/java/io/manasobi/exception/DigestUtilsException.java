package io.manasobi.exception;

/**
 * DigestUtils 클래스에서 발생한 예외 처리를 담당.
 * 
 * @author manasobi
 * @since 1.0.0
 *
 */
public class DigestUtilsException extends RuntimeException {

	private static final long serialVersionUID = -535743920065580694L;

	public DigestUtilsException(String msg) {
		super(msg);
	}

}
