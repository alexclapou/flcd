import java.util.ArrayList;
import java.util.List;

/*
mainprogram(){
    defINT a, b, maximum;
    input(a);

    input(b);
    maximum = 0;

    if(a > b){
        maximum = a;
    }
    else{
        maximum = b;
    }
    output(maximum);
}
 */

public class Main {
    public static void main(String[] args) {
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token("a"));
        tokens.add(new Token("b"));
        tokens.add(new Token("maximum"));
        tokens.add(new Token("a"));
        tokens.add(new Token("b"));
        tokens.add(new Token("maximum"));
        tokens.add(new Token("0"));
        tokens.add(new Token("a"));
        tokens.add(new Token("b"));
        tokens.add(new Token("maximum"));
        tokens.add(new Token("a"));
        tokens.add(new Token("maximum"));
        tokens.add(new Token("b"));
        tokens.add(new Token("maximum"));
        tokens.add(new Token("-3"));
        SortedTableSymbolTable symbolTable = new SortedTableSymbolTable();
        List<Token> pif = new ArrayList<>();
        for(var token:tokens) {
            symbolTable.add(token);
            pif.add(symbolTable.getToken(token.getSymbol()));
        }
        pif.forEach(System.out::println);
        System.out.println(' ');
        for(var x :symbolTable.tokens) {
            System.out.println(x.getSymbol() + ' ' + x.getIndex());
        }
        /*
        System.out.println(' ');
        for(var token:tokens)
            System.out.println("token " + token + " " + symbolTable.getPosition(token));
        System.out.println(' ');
        for(var t:symbolTable.tokens)
            System.out.println(t + " " + symbolTable.getPosition(t.toString()));
         */
    }
}