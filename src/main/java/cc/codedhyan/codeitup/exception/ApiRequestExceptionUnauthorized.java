package cc.codedhyan.codeitup.exception;

public class ApiRequestExceptionUnauthorized extends RuntimeException{
        public ApiRequestExceptionUnauthorized(String message) {
            super(message);
        }

        public ApiRequestExceptionUnauthorized(String message, Throwable cause) {
            super(message, cause);
        }
}
