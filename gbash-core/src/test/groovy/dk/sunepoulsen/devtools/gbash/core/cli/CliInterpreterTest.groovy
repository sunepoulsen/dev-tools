package dk.sunepoulsen.devtools.gbash.core.cli

import spock.lang.Specification

class CliInterpreterTest extends Specification {
    void "Find sub command with groups equals null"() {
        given: 'CliInterpreter with null groups'
            CliInterpreter cliInterpreter = new CliInterpreter(groups: null)

        expect: 'find sub command'
            cliInterpreter.findSubCommand('help') == null
    }

    void "Find sub command in single group"() {
        given: 'CliInterpreter with null groups'
            CliInterpreter cliInterpreter = new CliInterpreter(groups: [])

        and: 'has a single group of sub commands'
            SubCommandGroup group = new SubCommandGroup()
            cliInterpreter.getGroups().add(group)

        and: 'has a sub command that the group'
            SubCommandDef subCommandDef = Mock(SubCommandDef)
            group.getSubCommands().add(subCommandDef)
            group.getSubCommands().add(Mock(SubCommandDef))
            group.getSubCommands().add(Mock(SubCommandDef))

        when: 'find sub command'
            SubCommandDef result = cliInterpreter.findSubCommand('help')

        then:
            1 * subCommandDef.name() >> 'help'
            result == subCommandDef
    }

    void "Find sub command across multiple groups"() {
        given: 'CliInterpreter with null groups'
            CliInterpreter cliInterpreter = new CliInterpreter(groups: [])

        and: 'has a group with other sub commands'
            SubCommandGroup group = new SubCommandGroup()
            group.getSubCommands().add(Mock(SubCommandDef))
            group.getSubCommands().add(Mock(SubCommandDef))
            group.getSubCommands().add(Mock(SubCommandDef))
            cliInterpreter.getGroups().add(group)

        and: 'has a group with the sub command we are looking for'
            group = new SubCommandGroup()
            SubCommandDef subCommandDef = Mock(SubCommandDef)
            cliInterpreter.getGroups().add(group)

            group.getSubCommands().add(Mock(SubCommandDef))
            group.getSubCommands().add(Mock(SubCommandDef))
            group.getSubCommands().add(subCommandDef)
            group.getSubCommands().add(Mock(SubCommandDef))

        when: 'find sub command'
            SubCommandDef result = cliInterpreter.findSubCommand('help')

        then:
            1 * subCommandDef.name() >> 'help'
            result == subCommandDef
    }

    void "Find sub command that is added to multiple groups"() {
        given: 'CliInterpreter with null groups'
            CliInterpreter cliInterpreter = new CliInterpreter(groups: [])

        and: 'a sub command'
            SubCommandDef subCommandDef = Mock(SubCommandDef)

        and: 'has one group with the sub commands'
            SubCommandGroup group = new SubCommandGroup()
            group.getSubCommands().add(Mock(SubCommandDef))
            group.getSubCommands().add(subCommandDef)
            group.getSubCommands().add(Mock(SubCommandDef))
            cliInterpreter.getGroups().add(group)

        and: 'has another group with the sub commands'
            group = new SubCommandGroup()
            group.getSubCommands().add(Mock(SubCommandDef))
            group.getSubCommands().add(subCommandDef)
            group.getSubCommands().add(Mock(SubCommandDef))
            cliInterpreter.getGroups().add(group)

        when: 'find sub command'
            cliInterpreter.findSubCommand('help')

        then:
            2 * subCommandDef.name() >> 'help'
            IllegalArgumentException ex = thrown(IllegalArgumentException)
            ex.message == "The command 'help' exists in multiple groups"
    }

}
