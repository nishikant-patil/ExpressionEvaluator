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
        System.out.println(tokens);
        Stack<String> operandStack = new Stack<>();
        boolean result = true;
        for (String token : tokens) {
            if (token.equals("|") || token.equals("&")) {
                switch (token) {
                    case "|":
                        result &= Boolean.valueOf(operandStack.pop()) | Boolean.valueOf(operandStack.pop());
                        operandStack.push(result + "");
                        break;
                    case "&":
                        result &= Boolean.valueOf(operandStack.pop()) & Boolean.valueOf(operandStack.pop());
                        operandStack.push(result + "");
                        break;
                }
            } else {
                operandStack.push(token);
            }
        }
        return result;
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
