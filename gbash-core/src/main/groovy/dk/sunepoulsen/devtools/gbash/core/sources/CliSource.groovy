package dk.sunepoulsen.devtools.gbash.core.sources

import dk.sunepoulsen.devtools.gbash.core.exceptions.CliException

/**
 * A <code>CliInputSource</code> is a source of a file, url or integration to an external system that
 * can be resolved into a stream of bytes that can be handled by a script.
 */
interface CliSource {
    /**
     * "Filename" of the input source
     */
    String filename()

    /**
     * Returns an inputStream of this CliSource
     * <p>
     * Its the callers responsibility to close the InputStream
     */
    InputStream inputStream() throws CliException

    /**
     * Returns an outputStream for this CLISource that can be used to write to the source.
     * <p>
     * Its the callers responsibility to close the OutputStream
     */
    OutputStream outputStream() throws CliException
}
