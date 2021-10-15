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
        List<String> tokens = new ArrayList<>();
        tokens.add("a");
        tokens.add("b");
        tokens.add("maximum");
        tokens.add("a");
        tokens.add("b");
        tokens.add("maximum");
        tokens.add("0");
        tokens.add("a");
        tokens.add("b");
        tokens.add("maximum");
        tokens.add("a");
        tokens.add("maximum");
        tokens.add("b");
        tokens.add("maximum");
        tokens.add("-3");
        SortedTableSymbolTable symbolTable = new SortedTableSymbolTable();
        for(var token:tokens) {
            symbolTable.add(token);
            System.out.println("token " + token + " " + symbolTable.getPosition(token));
        }
        System.out.println(' ');
        for(var token:tokens)
            System.out.println("token " + token + " " + symbolTable.getPosition(token));
        System.out.println(' ');
        for(var t:symbolTable.tokens)
            System.out.println(t + " " + symbolTable.getPosition(t.toString()));
    }
}