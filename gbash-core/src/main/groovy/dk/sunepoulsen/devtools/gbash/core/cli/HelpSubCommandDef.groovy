package dk.sunepoulsen.devtools.gbash.core.cli

import dk.sunepoulsen.devtools.gbash.core.exceptions.CliException
import groovy.cli.picocli.CliBuilder
import groovy.cli.picocli.OptionAccessor

class HelpSubCommandDef implements SubCommandDef {
    SubCommandContainer subCommandContainer

    HelpSubCommandDef(SubCommandContainer subCommandContainer) {
        this.subCommandContainer = subCommandContainer
    }

    @Override
    String name() {
        return 'help'
    }

    @Override
    String description() {
        return 'Shows help to a specific sub command'
    }

    @Override
    CliBuilder cliBuilder() throws CliException {
        return new CliBuilder()
    }

    @Override
    SubCommandExecutor createExecutor(OptionAccessor optionAccessor) throws CliException {
        throw new UnsupportedOperationException('HelpSubCommandDef.createExecutor: Not implemented yet!')
    }
}
