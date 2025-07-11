package pl.bartkub.exercise.calculator;

public enum ErrorMessage {
    NEGATIVE_NUMBERS("Negative number(s) not allowed: {{numbers}}"),
    WRONG_DELIMITERS("Found invalid delimiter(s): {{delimiters}}");

    private final String text;

    ErrorMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}