package flows

import navigation.Router
import shared.MenuPrinter
import shared.UserInputReader
import storages.InMemoryStorage

class ArchiveSelection(
    private val userInputReader: UserInputReader,
    private val storage: InMemoryStorage,
    private val menuPrinter: MenuPrinter,
    private val router: Router
) {
    fun start() {
        showArchives()
        handleUserInput()
    }

    private fun showArchives() {
        menuPrinter.printMenu(
            "Основное меню",
            MenuPrinter.Type.ARCHIVE,
            storage.archives.map { it.name }
        )
    }

    private fun handleUserInput() {
        println("Выберите желаемое действие:")
        userInputReader.readNumber(
            createActionNumber = 0,
            exitActionNumber = storage.archives.size + 1,
            IntRange(1, storage.archives.size),
            onCreateAction = { router.openArchiveCreator() },
            onExitAction = { didTapExit() },
            onChooseAction = { router.openArchive(storage.archives[it - 1]) }
        )
    }

    private fun didTapExit() {
        println("Завершение программы")
    }
}