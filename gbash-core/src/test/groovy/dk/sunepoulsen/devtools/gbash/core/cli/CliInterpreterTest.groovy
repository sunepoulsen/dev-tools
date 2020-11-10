package dk.sunepoulsen.devtools.gbash.core.cli

import spock.lang.Specification

class CliInterpreterTest extends Specification {
    void "Find sub command with groups equals null"() {
        given: 'CliInterpreter with null groups'
            CliInterpreter cliInterpreter = new CliInterpreter(groups: null)

        expect: 'find sub command'
            cliInterpreter.findSubCommand('help') == null
    }
}
