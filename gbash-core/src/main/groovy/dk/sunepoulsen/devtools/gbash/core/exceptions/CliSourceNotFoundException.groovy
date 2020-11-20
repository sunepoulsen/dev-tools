package dk.sunepoulsen.devtools.gbash.core.exceptions

class CliSourceNotFoundException extends CliException {
    CliSourceNotFoundException(String message) {
        super(message)
    }

    CliSourceNotFoundException(String message, Throwable cause) {
        super(message, cause)
    }
}
