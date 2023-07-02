package flows

import models.Archive
import navigation.Router
import shared.MenuPrinter
import shared.UserInputReader

class ArchiveContent(
    private val userInputReader: UserInputReader,
    private val menuPrinter: MenuPrinter,
    private val router: Router,
    private val archive: Archive
) {
    fun start() {
        showMenu()
        handleUserInput()
    }

    private fun showMenu() {
        menuPrinter.printMenu(
            archive.name,
            MenuPrinter.Type.NOTES,
            archive.notes.map { it.title }
        )
    }

    private fun handleUserInput() {
        userInputReader.readNumber(
            createActionNumber = 0,
            exitActionNumber = archive.notes.size + 1,
            chooseRange = IntRange(1, archive.notes.size),
            onCreateAction = { router.openNotesCreator(archive) },
            onExitAction = { router.openArchiveSelection() },
            onChooseAction = {
                router.openNote(archive.notes[it - 1])
                start()
            }
        )
    }
}