package cc.codedhyan.codeitup.exception;

public class ApiRequestExceptionConflictRequest extends RuntimeException{
        public ApiRequestExceptionConflictRequest(String message) {
            super(message);
        }

        public ApiRequestExceptionConflictRequest(String message, Throwable cause) {
            super(message, cause);
        }
}
