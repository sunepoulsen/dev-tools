package dk.sunepoulsen.devtools.gbash.core.cli

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
    List<SubCommandGroup> groups

    SubCommandDef findSubCommand(String name) {
        SubCommandDef result

        groups.each {SubCommandGroup group ->
            SubCommandDef subCommandDef = group.subCommands.find {it.name() == name }
            if (subCommandDef == null) {
                return
            }

            if (result == null) {
                result = subCommandDef
            }
            else {
                throw new IllegalArgumentException("The command '${name}' exists in multiple groups")
            }
        }

        return result
    }
}
