package leare.apiGateway.errors;

public class AuthError extends Exception {
    public AuthError(String message) {
        super(message);
    }
}