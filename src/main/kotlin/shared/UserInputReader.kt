package shared

import java.util.Scanner

class UserInputReader(private val scanner: Scanner) {

    enum class ExpectedUserInput(val regex: Regex) {
        NUMBER(Regex("^-?(?!0\\d+)\\d*")),
        TEXT(Regex("^(?! *\$).+"))
    }

    fun readNumber(
        createActionNumber: Int,
        exitActionNumber: Int,
        chooseRange: IntRange,
        onCreateAction: () -> Unit,
        onExitAction: () -> Unit,
        onChooseAction: (Int) -> Unit
    ) {
        val input = readUserInput(ExpectedUserInput.NUMBER)
        if (input == "Выход") {
            onExitAction()
        } else {
            when (val digit = input.toInt()) {
                createActionNumber -> onCreateAction()
                exitActionNumber -> onExitAction()
                in chooseRange -> onChooseAction(digit)
                else -> {
                    println("Введенное число за рамками корректного диапазона, введите снова:")
                    readNumber(createActionNumber, exitActionNumber, chooseRange, onCreateAction, onExitAction, onChooseAction)
                }
            }
        }
    }

    fun readText(
        onExitAction: () -> Unit,
        onGettingTextAction: (String) -> Unit
    ) {
        when (val input = readUserInput(ExpectedUserInput.TEXT)) {
            "Выход" -> { onExitAction() }
            else -> { onGettingTextAction(input) }
        }
    }

    fun readAnyCharacters() {
        scanner.nextLine()
    }

    private fun readUserInput(expectedInput: ExpectedUserInput): String {
        var isValid = false
        var value = ""
        while (!isValid) {
            value = scanner.nextLine()
            isValid = (expectedInput.regex.matches(value) && value != "") || value == "Выход"

            if (!isValid) {
                printIncorrectInputMessage(expectedInput)
            }
        }
        return value
    }

    private fun printIncorrectInputMessage(expectedInput: ExpectedUserInput) {
        val message = when (expectedInput) {
            ExpectedUserInput.NUMBER -> "Введено неверное значение. Введите число или \"Выход\""
            ExpectedUserInput.TEXT -> "Введено неверное значение, ожидается текст. Для выхода введите \"Выход\""
        }
        println(message)
    }
}