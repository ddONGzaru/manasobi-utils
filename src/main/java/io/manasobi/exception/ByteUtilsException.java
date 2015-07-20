package io.manasobi.exception;

/**
 * ByteUtils 클래스에서 발생한 예외 처리를 담당.
 * 
 * @author manasobi
 * @since 1.0.0
 *
 */
public class ByteUtilsException extends RuntimeException {

	private static final long serialVersionUID = -7490832949014151286L;
	
	public ByteUtilsException(String msg) {
		super(msg);
	}

}
