package cc.codedhyan.codeitup.exception;

public class ApiRequestExceptionConflict extends RuntimeException{
        public ApiRequestExceptionConflict(String message) {
            super(message);
        }

        public ApiRequestExceptionConflict(String message, Throwable cause) {
            super(message, cause);
        }
}
