package ExpressionEvaluator.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

public class Calculator {
    private static Pattern fieldNamePattern = Pattern.compile("[A-Za-z]+");
    public static BigDecimal calculate(Object object, List<String> tokens) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        insertValuesForPlaceholders(object, tokens);
        Stack<String> operandStack = new Stack<>();
        BigDecimal result = null;
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                switch (token) {
                    case "+":
                        String a = operandStack.pop();
                        String b = operandStack.pop();
                        result = new BigDecimal(b).add(new BigDecimal(a));
                        operandStack.push(result.toString());
                        break;
                    case "-":
                        a = operandStack.pop();
                        b = operandStack.pop();
                        result = new BigDecimal(b).subtract(new BigDecimal(a));
                        operandStack.push(result.toString());
                        break;
                    case "*":
                        a = operandStack.pop();
                        b = operandStack.pop();
                        result = new BigDecimal(b).multiply(new BigDecimal(a));
                        operandStack.push(result.toString());
                        break;
                    case "/":
                        a = operandStack.pop();
                        b = operandStack.pop();
                        result = new BigDecimal(b).divide(new BigDecimal(a));
                        operandStack.push(result.toString());
                        break;
                }
            } else {
                operandStack.push(token);
            }
        }
        return result;
    }

    private static void insertValuesForPlaceholders(Object object, List<String> tokens) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for (int i = 0; i != tokens.size(); ++i) {
            if (fieldNamePattern.matcher(tokens.get(i)).matches()) {
                String value = getLOp(object, tokens.get(i));
                tokens.set(i, value);
            }
        }
    }

    private static String getLOp(Object object, String operand) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (object instanceof Map) {
            return (String) ((Map) object).get(operand);
        }
        Method m;
        for (String token : operand.split("\\.")) {
            m = object.getClass().getMethod("get" + token);
            object = m.invoke(object);
        }
        return object.toString();
    }
}
