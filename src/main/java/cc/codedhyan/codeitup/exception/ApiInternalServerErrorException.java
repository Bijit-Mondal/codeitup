package cc.codedhyan.codeitup.exception;

public class ApiInternalServerErrorException extends RuntimeException {
    public ApiInternalServerErrorException(String message) {
        super(message);
    }

    public ApiInternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
