package dk.sunepoulsen.devtools.gbash.core.cli

import spock.lang.Specification

class SubCommandContainerTest extends Specification {
    void "Find sub command with groups equals null"() {
        given: 'SubCommandContainer with null groups'
            SubCommandContainer container = new SubCommandContainer(groups: null)

        expect: 'find sub command'
            container.findSubCommand('help') == null
    }

    void "Find sub command in single group"() {
        given: 'SubCommandContainer with null groups'
            SubCommandContainer container = new SubCommandContainer(groups: [])

        and: 'has a single group of sub commands'
            SubCommandGroup group = new SubCommandGroup()
            container.getGroups().add(group)

        and: 'has a sub command that the group'
            SubCommandDef subCommandDef = Mock(SubCommandDef)
            group.getSubCommands().add(subCommandDef)
            group.getSubCommands().add(Mock(SubCommandDef))
            group.getSubCommands().add(Mock(SubCommandDef))

        when: 'find sub command'
            SubCommandDef result = container.findSubCommand('help')

        then:
            1 * subCommandDef.name() >> 'help'
            result == subCommandDef
    }

    void "Find sub command across multiple groups"() {
        given: 'SubCommandContainer with null groups'
            SubCommandContainer container = new SubCommandContainer(groups: [])

        and: 'has a group with other sub commands'
            SubCommandGroup group = new SubCommandGroup()
            group.getSubCommands().add(Mock(SubCommandDef))
            group.getSubCommands().add(Mock(SubCommandDef))
            group.getSubCommands().add(Mock(SubCommandDef))
            container.getGroups().add(group)

        and: 'has a group with the sub command we are looking for'
            group = new SubCommandGroup()
            SubCommandDef subCommandDef = Mock(SubCommandDef)
            container.getGroups().add(group)

            group.getSubCommands().add(Mock(SubCommandDef))
            group.getSubCommands().add(Mock(SubCommandDef))
            group.getSubCommands().add(subCommandDef)
            group.getSubCommands().add(Mock(SubCommandDef))

        when: 'find sub command'
            SubCommandDef result = container.findSubCommand('help')

        then:
            1 * subCommandDef.name() >> 'help'
            result == subCommandDef
    }

    void "Find sub command that is added to multiple groups"() {
        given: 'SubCommandContainer with null groups'
            SubCommandContainer container = new SubCommandContainer(groups: [])

        and: 'a sub command'
            SubCommandDef subCommandDef = Mock(SubCommandDef)

        and: 'has one group with the sub commands'
            SubCommandGroup group = new SubCommandGroup()
            group.getSubCommands().add(Mock(SubCommandDef))
            group.getSubCommands().add(subCommandDef)
            group.getSubCommands().add(Mock(SubCommandDef))
            container.getGroups().add(group)

        and: 'has another group with the sub commands'
            group = new SubCommandGroup()
            group.getSubCommands().add(Mock(SubCommandDef))
            group.getSubCommands().add(subCommandDef)
            group.getSubCommands().add(Mock(SubCommandDef))
            container.getGroups().add(group)

        when: 'find sub command'
            container.findSubCommand('help')

        then:
            2 * subCommandDef.name() >> 'help'
            IllegalArgumentException ex = thrown(IllegalArgumentException)
            ex.message == "The command 'help' exists in multiple groups"
    }

}
