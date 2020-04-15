package net.fashiongo.webadmin.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 인증 토큰 만료 및 부정확한 Token 일 경우 발생.
 */
public class TokenInvalidException extends AuthenticationException {

    /**
     * Instantiates a new Token invalid exception.
     *
     * @param msg the msg
     * @param t   the t
     */
    public TokenInvalidException(String msg, Throwable t) {
        super(msg, t);
    }

    /**
     * Instantiates a new Token invalid exception.
     *
     * @param msg the msg
     */
    public TokenInvalidException(String msg) {
        super(msg);
    }
}
