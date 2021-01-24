package dk.sunepoulsen.devtools.scripts.xml.cli

import dk.sunepoulsen.devtools.gbash.core.exceptions.CliException
import dk.sunepoulsen.devtools.gbash.core.cli.SubCommandDef
import dk.sunepoulsen.devtools.gbash.core.cli.SubCommandExecutor
import dk.sunepoulsen.devtools.gbash.core.sources.CliFileSource
import groovy.cli.picocli.CliBuilder
import groovy.cli.picocli.OptionAccessor

class FormatSubCommandDef implements SubCommandDef {
    @Override
    String name() {
        return 'format'
    }

    @Override
    String description() {
        return 'Formats an input as well formed XML'
    }

    @Override
    CliBuilder cliBuilder() throws CliException {
        return new CliBuilder()
    }

    @Override
    SubCommandExecutor createExecutor(OptionAccessor optionAccessor) throws CliException {
        if (optionAccessor.arguments().empty) {
            throw new CliException('Missing input')
        }

        if (optionAccessor.arguments().size() > 1) {
            throw new CliException('Too many inputs')
        }

        return new FormatSubCommandExecutor(new CliFileSource(new File(optionAccessor.arguments().first())))
    }
}
