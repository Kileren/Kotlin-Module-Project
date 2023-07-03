package flows

import models.Note
import shared.UserInputReader

class NoteReader(
    private val userInputReader: UserInputReader,
    private val note: Note
) {
    fun start() {
        printNoteText()
        handleUserInput()
    }

    private fun printNoteText() {
        println("\n${note.text}")
    }

    private fun handleUserInput() {
        println("\nДля продолжения введите любой символ или нажмите Enter...")
        userInputReader.readAnyCharacters()
    }
}