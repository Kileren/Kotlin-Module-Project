package flows

import models.Archive
import models.Note
import navigation.Router
import shared.UserInputReader
import storages.InMemoryStorage

class NotesCreator(
    private val userInputReader: UserInputReader,
    private val storage: InMemoryStorage,
    private val router: Router,
    private val archive: Archive
) {
    fun start() {
        readTitle()
    }

    private fun readTitle() {
        println("Введите название заметки:")
        userInputReader.readText(
            onExitAction = { onExitAction() },
            onGettingTextAction = { readText(title = it) }
        )
    }

    private fun readText(title: String) {
        println("Введите текст заметки:")
        userInputReader.readText(
            onExitAction = { onExitAction() },
            onGettingTextAction = { createNote(title, it) }
        )
    }

    private fun createNote(title: String, text: String) {
        val note = Note(title, text)
        storage.addNoteTo(archive, note)
        onExitAction()
    }

    private fun onExitAction() {
        router.openArchive(archive)
    }
}