package org.aruizeac;

import java.util.HashSet;

public class DijkstraArithmeticInterpretator {
    private static final Character CLOSING_PARENTHESIS = ')';
    private static final Character OPENING_PARENTHESIS = '(';
    private static final HashSet<Character> OPERATOR_MAP = new HashSet<>() {{
        add('*');
        add('/');
        add('+');
        add('-');
    }};

    public static Double execute(String operation) {
        if (operation.length() == 0) return null;

        Stack<Character> operators = new LinkedStack<>();
        Stack<Double> values = new LinkedStack<>();
        StringBuilder value = new StringBuilder();

        for (int i = 0; i < operation.length(); i++) {
            Character c = operation.charAt(i);
            if (c.equals(OPENING_PARENTHESIS)) continue;
            if (!OPERATOR_MAP.contains(c) && !c.equals(CLOSING_PARENTHESIS)) {
                value.append(c);
                if (i < operation.length()-1) continue;
                values.push(Double.parseDouble(value.toString()));
                value.setLength(0);
            } else if (c.equals(CLOSING_PARENTHESIS) || OPERATOR_MAP.contains(c)) {
                if (!value.isEmpty()) {
                    values.push(Double.parseDouble(value.toString()));
                    value.setLength(0);
                }
            }

            if (c.equals(CLOSING_PARENTHESIS) || (i == operation.length()-1 && values.count() > 1)) {
                Double a = values.pop(), b = values.pop();
                Character operator = operators.pop();
                switch (operator) {
                    case '*' -> values.push(a * b);
                    case '/' -> values.push(a / b);
                    case '-' -> values.push(a - b);
                    case '+' -> values.push(a + b);
                }
            } else if (OPERATOR_MAP.contains(c)) {
                operators.push(c);
            }
        }

        return values.pop();
    }
}