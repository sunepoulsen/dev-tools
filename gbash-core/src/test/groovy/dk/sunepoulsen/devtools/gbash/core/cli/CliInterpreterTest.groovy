package dk.sunepoulsen.devtools.gbash.core.cli

import groovy.cli.picocli.CliBuilder
import spock.lang.Specification;

import static org.junit.jupiter.api.Assertions.*;

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
            SubCommandExecutor result = cliInterpreter.parse([])

        then:
            0 * container._
            0 * subCommandDef._
            result == null
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

    void "Parse sub command with illegal arguments"() {
        when:
            SubCommandExecutor result = cliInterpreter.parse(['cmd'])

        then:
            1 * container.findSubCommand(_) >> subCommandDef
            1 * subCommandDef.cliBuilder() >> new CliBuilder()
            1 * subCommandDef.createExecutor(_) >> subCommandExecutor
            result == subCommandExecutor
    }
}
