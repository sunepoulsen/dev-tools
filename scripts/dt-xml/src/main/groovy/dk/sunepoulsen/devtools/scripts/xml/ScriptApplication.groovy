package dk.sunepoulsen.devtools.scripts.xml

import dk.sunepoulsen.devtools.gbash.core.cli.CliInterpreter
import dk.sunepoulsen.devtools.gbash.core.cli.HelpSubCommandDef
import dk.sunepoulsen.devtools.gbash.core.cli.SubCommandContainer
import dk.sunepoulsen.devtools.gbash.core.cli.SubCommandGroup
import dk.sunepoulsen.devtools.scripts.xml.cli.FormatSubCommandDef

class ScriptApplication {
    static void main(String[] args) {
        CliInterpreter cliInterpreter = new CliInterpreter(
            container: new SubCommandContainer(
                groups: [
                    new SubCommandGroup(
                        header: 'Default XML commands',
                        subCommands: [
                            new HelpSubCommandDef(),
                            new FormatSubCommandDef()
                        ]
                    )
                ]
            )
        )

        cliInterpreter.execute(args)
    }
}
