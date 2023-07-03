package storages

import models.Archive
import models.Note

class InMemoryStorage {

    var archives: ArrayList<Archive> = arrayListOf()

    fun addArchive(archive: Archive) {
        archives.add(archive)
    }

    fun addNoteTo(archive: Archive, note: Note) {
        archives.first { it.name == archive.name }.notes.add(note)
    }
}