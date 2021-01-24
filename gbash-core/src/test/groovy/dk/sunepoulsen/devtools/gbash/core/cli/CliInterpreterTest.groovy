package dk.sunepoulsen.devtools.gbash.core.cli

import dk.sunepoulsen.devtools.gbash.core.exceptions.CliException
import groovy.cli.picocli.CliBuilder
import spock.lang.Specification

class CliInterpreterTest extends Specification {
    private CliInterpreter cliInterpreter
    private SubCommandContainer container

    private SubCommandDef subCommandDef
    private SubCommandExecutor subCommandExecutor

    void setup() {
        this.container = Mock(SubCommandContainer)
        this.cliInterpreter = new CliInterpreter(container: container)
        this.subCommandDef = Mock(SubCommandDef)
        this.subCommandExecutor = Mock(SubCommandExecutor)
    }

    void "Parsing null arguments"() {
        when: 'parse null arguments'
            cliInterpreter.parse(null)

        then: 'expect exception'
            thrown(IllegalArgumentException)
            0 * container._
            0 * subCommandDef._
    }

    void "Parsing empty list of arguments"() {
        when: 'parse empty list of arguments'
            cliInterpreter.parse([])

        then:
            0 * container._
            0 * subCommandDef._
            thrown(CliException)
    }

    void "Parse sub command successfully"() {
        when:
            SubCommandExecutor result = cliInterpreter.parse(['cmd'])

        then:
            1 * container.findSubCommand(_) >> subCommandDef
            1 * subCommandDef.cliBuilder() >> new CliBuilder()
            1 * subCommandDef.createExecutor(_) >> subCommandExecutor
            result == subCommandExecutor
    }

    void "Parse sub command with bad executor"() {
        when:
            cliInterpreter.parse(['cmd'])

        then:
            1 * container.findSubCommand(_) >> subCommandDef
            1 * subCommandDef.cliBuilder() >> new CliBuilder()
            1 * subCommandDef.createExecutor(_) >> { throw new CliException('message') }
            thrown(CliException)
    }

    void "Parse arguments with unknown sub command"() {
        when:
            cliInterpreter.parse(['cmd'])

        then:
            1 * container.findSubCommand(_) >> null
            0 * subCommandDef.cliBuilder()
            0 * subCommandDef.createExecutor(_)
            thrown(CliException)
    }

    void "Parse no arguments"() {
        when:
            cliInterpreter.parse([])

        then:
            0 * container.findSubCommand(_)
            0 * subCommandDef.cliBuilder()
            0 * subCommandDef.createExecutor(_)
            thrown(CliException)
    }
}
