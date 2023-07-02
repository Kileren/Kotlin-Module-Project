package di

import flows.*
import models.Archive
import models.Note
import navigation.Router
import shared.MenuPrinter
import shared.UserInputReader
import storages.InMemoryStorage
import java.util.Scanner

class Builder(
    private val router: Router
) {
    private val scanner = Scanner(System.`in`)
    private val userInputReader = UserInputReader(scanner)
    private val inMemoryStorage = InMemoryStorage()
    private val menuPrinter = MenuPrinter()

    fun buildArchiveSelection(): ArchiveSelection {
        return ArchiveSelection(userInputReader, inMemoryStorage, menuPrinter, router)
    }

    fun buildArchiveCreator(): ArchiveCreator {
        return ArchiveCreator(userInputReader, inMemoryStorage, router)
    }

    fun buildArchiveContent(archive: Archive): ArchiveContent {
        return ArchiveContent(userInputReader, menuPrinter, router, archive)
    }

    fun buildNotesCreator(archive: Archive): NotesCreator {
        return NotesCreator(userInputReader, inMemoryStorage, router, archive)
    }

    fun buildNoteReader(note: Note): NoteReader {
        return NoteReader(userInputReader, note)
    }
}