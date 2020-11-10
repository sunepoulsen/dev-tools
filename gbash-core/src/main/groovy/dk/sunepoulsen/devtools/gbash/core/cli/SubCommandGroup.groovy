package dk.sunepoulsen.devtools.gbash.core.cli

class SubCommandGroup {
    /**
     * Header of this group of subcommands,
     * <p>
     *     It is used by the help screen to provide a help to the user about the domain or general use of the
     *     sub commands in the group.
     * </p>
     */
    String header

    /**
     * List of sub commands in the group.
     */
    List<SubCommandDef> subCommands
}
