package shared

class MenuPrinter {

    enum class Type {
        ARCHIVE,
        NOTES
    }

    fun printMenu(
        mainTitle: String,
        type: Type,
        titles: List<String>
    ) {
        val createButtonTitle = when (type) {
            Type.ARCHIVE -> "Создать архив"
            Type.NOTES -> "Создать заметку"
        }
        println("\n$mainTitle")
        println("0. $createButtonTitle")
        for ((index, title) in titles.withIndex()) {
            println("${index + 1}. $title")
        }
        println("${titles.size + 1}. Выход")
    }
}