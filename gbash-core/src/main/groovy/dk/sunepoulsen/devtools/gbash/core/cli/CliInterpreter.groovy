package dk.sunepoulsen.devtools.gbash.core.cli

import dk.sunepoulsen.devtools.gbash.core.exceptions.CliException
import groovy.cli.picocli.OptionAccessor

/**
 * Interpreter that supports usage of subcommands to a script.
 * <p>
 *  A script that uses subcommands supports the following syntax:
 *  <pre>
 *      <dt-script> <subcommand> [options] [args]
 *  </pre>
 * </p>
 */
class CliInterpreter {
    SubCommandContainer container

    CliInterpreter() {
        this.container = new SubCommandContainer()
    }

    /**
     * Parse a list of arguments and returns an executor that can execute the logic.
     *
     * @param args List of arguments.
     *
     * @return An executor if parsing was successful.
     *
     * @throws dk.sunepoulsen.devtools.gbash.core.exceptions.CliException Thrown in case of parse errors.
     */
    SubCommandExecutor parse(List<String> args) throws CliException {
        if (args == null) {
            throw new IllegalArgumentException('args must not be null')
        }

        if (args.empty) {
            throw new CliException('Command is missing')
        }

        SubCommandDef subCommandDef = container.findSubCommand(args[0])
        if (subCommandDef == null) {
            throw new CliException("Command '${args[0]}' is unknown")
        }

        List<String> subArgs = args
        subArgs.remove(0)
        OptionAccessor optionAccessor = subCommandDef.cliBuilder().parse(subArgs)
        return subCommandDef.createExecutor(optionAccessor)
    }

    void execute(String[] args) {
        try {
            SubCommandExecutor executor = parse(args.toList())
            executor.execute()
        }
        catch( Exception ex ) {
            System.out.newWriter().println(ex.getMessage())
            ex.printStackTrace(System.out)
        }
    }
}
