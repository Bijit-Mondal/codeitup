package cc.codedhyan.codeitup.exception;


public class ApiRequestExceptionBadRequest extends RuntimeException {

        public ApiRequestExceptionBadRequest(String message) {
            super(message);
        }

        public ApiRequestExceptionBadRequest(String message, Throwable cause) {
            super(message, cause);
        }
}
