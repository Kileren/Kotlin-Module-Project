package navigation
import di.Builder
import models.Archive
import models.Note

interface Router {
    fun openArchiveSelection()
    fun openArchiveCreator()
    fun openArchive(archive: Archive)
    fun openNotesCreator(archive: Archive)
    fun openNote(note: Note)
}

class Coordinator: Router {

    private val builder = Builder(this)

    fun start() {
        openArchiveSelection()
    }

    // Router methods

    override fun openArchiveSelection() {
        builder.buildArchiveSelection().start()
    }

    override fun openArchiveCreator() {
        builder.buildArchiveCreator().askForNewArchiveName()
    }

    override fun openArchive(archive: Archive) {
        builder.buildArchiveContent(archive).start()
    }

    override fun openNotesCreator(archive: Archive) {
        builder.buildNotesCreator(archive).start()
    }

    override fun openNote(note: Note) {
        builder.buildNoteReader(note).start()
    }
}