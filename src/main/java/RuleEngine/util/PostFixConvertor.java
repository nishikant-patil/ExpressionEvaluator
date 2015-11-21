package RuleEngine.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by Nishikant on 9/6/2015.
 * Converts the expression provided in Infix format to a Postfix one.
 */
public class PostFixConvertor {
    public static List<String> convertToPostFix(String expression){
        String postFixExpr = getPostFixExpr(expression);
        List<String> tokens = new ArrayList<>(Arrays.asList(postFixExpr.split("`")));
        removeBlankTokens(tokens);
        return tokens;
    }

    private static String getPostFixExpr(String expression) {
        Stack<String> opStack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        for(char c : expression.toCharArray()){
            if('('==c || '&'==c || '|'==c || ')'==c){
                builder.append('`');
                if(')'==c){
                    while(!opStack.empty()){
                        String operator = opStack.pop();
                        if(operator.equals("(")) { break; }
                        builder.append(operator).append("`");
                    }
                } else {
                    opStack.push(c + "");
                }
            } else {
                builder.append(c);
            }
        }
        while(!opStack.empty()){
            String operator = opStack.pop();
            if(operator.equals("(")) { break; }
            builder.append(operator).append("`");
        }
        return builder.toString().replace("``", "`");
    }

    private static void removeBlankTokens(List<String> tokens) {
        tokens.remove(" ");
        for (int i=0; i!=tokens.size(); ++i){
            tokens.set(i, tokens.get(i).trim());
        }
    }
}
