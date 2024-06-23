package org.example.authorizationservice.exceptions;

public class InvalidNicknameException extends RuntimeException {
    public InvalidNicknameException(String message) {
        super(message);
    }
}
