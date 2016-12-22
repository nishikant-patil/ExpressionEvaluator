package ExpressionEvaluator.util;

import java.util.*;

/**
 * Created by Nishikant on 9/6/2015.
 * Converts the expression provided in Infix format to a Postfix one.
 */
public class PostFixConverter {
    public static List<String> convertToPostFix(String expression) {
        String postFixExpr = getPostFixExpr(expression);
        List<String> tokens = new ArrayList<>(Arrays.asList(postFixExpr.split("`")));
        removeBlankTokens(tokens);
        return tokens;
    }

    private static String getPostFixExpr(String expression) {
        Stack<String> opStack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if ('(' == c || '&' == c || '|' == c || ')' == c || '+' == c || '-' == c || '*' == c || '/' == c) {
                builder.append('`');
                if (')' == c || '-' == c || '+' == c) {
                    while (!opStack.empty()) {
                        String operator = opStack.pop();
                        if (operator.equals("(")) {
                            break;
                        }
                        builder.append(operator).append("`");
                    }
                    if ('-' == c || '+' == c) {
                        opStack.add(c + "");
                    }
                } else {
                    opStack.push(c + "");
                }
            } else {
                builder.append(c);
            }
        }
        while (!opStack.empty()) {
            String operator = opStack.pop();
            if (operator.equals("(")) {
                break;
            }
            builder.append("`").append(operator).append("`");
        }
        return builder.toString().replace("  ", " ");
    }

    private static void removeBlankTokens(List<String> tokens) {
        tokens.remove(" ");
        tokens.removeAll(Collections.singletonList(""));
        for (int i = 0; i != tokens.size(); ++i) {
            tokens.set(i, tokens.get(i).trim());
        }
    }
}
