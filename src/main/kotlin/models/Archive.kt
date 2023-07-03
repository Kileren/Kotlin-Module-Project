package models

data class Archive(
    val name: String,
    val notes: ArrayList<Note> = arrayListOf()
)