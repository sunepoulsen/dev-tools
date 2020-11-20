package dk.sunepoulsen.devtools.gbash.core.exceptions

class CliException extends Exception {
    CliException(String message) {
        super(message)
    }

    CliException(String message, Throwable cause) {
        super(message, cause)
    }
}
