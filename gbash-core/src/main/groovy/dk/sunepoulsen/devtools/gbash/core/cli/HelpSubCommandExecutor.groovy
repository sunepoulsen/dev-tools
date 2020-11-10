package dk.sunepoulsen.devtools.gbash.core.cli

class HelpSubCommandExecutor implements SubCommandExecutor {
    SubCommandDef subCommandDef

    HelpSubCommandExecutor(SubCommandDef subCommandDef) {
        this.subCommandDef = subCommandDef
    }

    @Override
    void execute() {
        throw new UnsupportedOperationException('Not implemented yet!')
    }
}
