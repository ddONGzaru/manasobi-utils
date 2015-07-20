package io.manasobi.exception;

/**
 * IOUtils 클래스에서 발생한 예외 처리를 담당.
 * 
 * @author manasobi
 * @since 1.0.0
 *
 */
public class IOUtilsException extends RuntimeException {

	private static final long serialVersionUID = 5929407517313732404L;

	public IOUtilsException(String msg) {
		super(msg);
	}

}
