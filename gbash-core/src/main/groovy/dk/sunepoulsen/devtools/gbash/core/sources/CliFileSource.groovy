package dk.sunepoulsen.devtools.gbash.core.sources

import dk.sunepoulsen.devtools.gbash.core.exceptions.CliException
import dk.sunepoulsen.devtools.gbash.core.exceptions.CliSourceNotFoundException

class CliFileSource implements CliSource {
    File file

    CliFileSource(File file) {
        this.file = file
    }

    @Override
    String filename() {
        return file.name
    }

    @Override
    InputStream inputStream() throws CliException {
        if (file.exists()) {
            return new FileInputStream(file)
        }

        throw new CliSourceNotFoundException("The file '${filename()}' does not exist")
    }

    @Override
    OutputStream outputStream() throws CliException {
        return new FileOutputStream(file)
    }
}
