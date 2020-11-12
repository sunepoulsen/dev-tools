package dk.sunepoulsen.devtools.gbash.core.cli

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
    CliBuilder cliBuilder() {
        return new CliBuilder()
    }

    @Override
    SubCommandExecutor createExecutor(OptionAccessor optionAccessor) {
        throw new UnsupportedOperationException('Not implemented yet!')
    }
}
