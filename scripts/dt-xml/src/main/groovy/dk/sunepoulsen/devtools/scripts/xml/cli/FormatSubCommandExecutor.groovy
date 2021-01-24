package dk.sunepoulsen.devtools.scripts.xml.cli

import dk.sunepoulsen.devtools.gbash.core.cli.SubCommandExecutor
import dk.sunepoulsen.devtools.gbash.core.sources.CliSource
import groovy.xml.XmlSlurper
import groovy.xml.XmlUtil
import groovy.xml.slurpersupport.GPathResult

class FormatSubCommandExecutor implements SubCommandExecutor {
    CliSource cliSource

    FormatSubCommandExecutor(CliSource cliSource) {
        this.cliSource = cliSource
    }

    @Override
    void execute() {
        XmlSlurper xmlSlurper = new XmlSlurper()

        try(InputStream is = cliSource.inputStream()) {
            GPathResult xml = xmlSlurper.parse(is)
            System.out.print(XmlUtil.serialize(xml))
        }
    }
}
