package flows

import models.Archive
import navigation.Router
import shared.UserInputReader
import storages.InMemoryStorage

class ArchiveCreator(
    private val userInputReader: UserInputReader,
    private val storage: InMemoryStorage,
    private val router: Router
) {
    fun askForNewArchiveName() {
        println("Введите название для архива:")
        userInputReader.readText(
            onExitAction = { router.openArchiveSelection() },
            onGettingTextAction = {
                storage.addArchive(Archive(it))
                router.openArchiveSelection()
            }
        )
    }
}