package ExpressionEvaluator.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Stack;

/**
 * Created by Nishikant on 9/6/2015.
 * Evaluates a given Postfix expression to a boolean result.
 */
public class Evaluator {
    public static boolean evaluate(Object object, List<String> tokens) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        convertEqualsToBoolean(object, tokens);
        Stack<String> operandStack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("|") || token.equals("&")) {
                switch (token) {
                    case "|" -> operandStack.push((Boolean.parseBoolean(operandStack.pop()) | Boolean.parseBoolean(operandStack.pop())) + "");
                    case "&" -> operandStack.push((Boolean.parseBoolean(operandStack.pop()) & Boolean.parseBoolean(operandStack.pop())) + "");
                }
            } else {
                operandStack.push(token);
            }
        }
        return Boolean.parseBoolean(operandStack.pop());
    }

    private static void convertEqualsToBoolean(Object object, List<String> tokens) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for (int i = 0; i != tokens.size(); ++i) {
            if (tokens.get(i).contains("=")) {
                String[] operands = tokens.get(i).split("=");
                String lOp = getLOp(object, operands[0]);
                String rOp = operands[1];
                tokens.set(i, lOp.equals(rOp) + "");
            }
        }
    }

    private static String getLOp(Object object, String operand) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m;
        for (String token : operand.split("\\.")) {
            m = object.getClass().getMethod("get" + token);
            object = m.invoke(object);
        }
        return object.toString();
    }
}
