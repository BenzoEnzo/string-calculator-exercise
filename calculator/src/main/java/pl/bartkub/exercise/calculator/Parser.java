package pl.bartkub.exercise.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
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

    public List<String> divideInput(String input) {
        if (input.startsWith("//")) {
            input = input.substring(input.indexOf('\n') + 1);
        }

        List<String> parts = new ArrayList<>();

        Matcher matcher = Pattern.compile("-?\\d+").matcher(input);

        int index = 0;

        while (matcher.find()) {
            int start = matcher.start();

            if (index < start)
                parts.add(input.substring(index, start));

            parts.add(matcher.group());

            index = matcher.end();
        }

        if (index < input.length()) {
            parts.add(input.substring(index));
        }

        return parts;
    }
}
