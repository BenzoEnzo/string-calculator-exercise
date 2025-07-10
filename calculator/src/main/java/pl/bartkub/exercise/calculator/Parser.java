package pl.bartkub.exercise.calculator;

import java.util.regex.Pattern;

public class Parser {
    public Parser() {
    }

    public String extractDelimiter(String text) {
        if (text.startsWith("//")) {
            int newlineIndex = text.indexOf('\n');
            return Pattern.quote(text.substring(2, newlineIndex));
        }

        return "[,\n]";
    }
}
