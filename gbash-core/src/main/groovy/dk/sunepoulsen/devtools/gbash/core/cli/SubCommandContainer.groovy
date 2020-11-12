package dk.sunepoulsen.devtools.gbash.core.cli

/**
 * A container of sub commands with helper functions to operate on the container.
 */
class SubCommandContainer {
    /**
     * List of groups of sub commands
     */
    List<SubCommandGroup> groups = []

    /**
     * Find a sub command by its name
     *
     * @param name The name of the sub command.
     *
     * @return The sub command if it exists. <code>null</code> otherwise.
     *
     * @exception IllegalArgumentException if more than one instance of the sub command is found in the container.
     */
    SubCommandDef findSubCommand(String name) {
        SubCommandDef result

        groups.each { SubCommandGroup group ->
            SubCommandDef subCommandDef = group.getSubCommands().find {
                it.name() == name
            }

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
