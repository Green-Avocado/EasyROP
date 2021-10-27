package persistence;

// Represents a checked exception where the type of ExploitObject and the expected type do not match.
public class TypeMismatchException extends Exception {

    // EFFECTS: Creates a new TypeMismatchException with the given error message.
    public TypeMismatchException(String error) {
        super(error);
    }
}
