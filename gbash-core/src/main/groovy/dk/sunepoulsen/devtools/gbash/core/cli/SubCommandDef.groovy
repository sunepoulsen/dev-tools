package dk.sunepoulsen.devtools.gbash.core.cli

import groovy.cli.picocli.CliBuilder
import groovy.cli.picocli.OptionAccessor

/**
 * Definition of a sub command.
 */
interface SubCommandDef {
    /**
     * The name of the sub command
     */
    String name()

    /**
     * a small description of the usage of the sub command
     */
    String description()

    /**
     * CliBuilder to parse the options and arguments to the sub command
     */
    CliBuilder cliBuilder() throws CliException

    SubCommandExecutor createExecutor(OptionAccessor optionAccessor) throws CliException
}