import java.util.*;

public class SortedTableSymbolTable {
    List<Token> tokens;

    public SortedTableSymbolTable() {
        tokens = new ArrayList<>();
    }

    public void add(Token symbol){
        if(!find(symbol.getSymbol())){
            tokens.add(symbol);
            tokens.sort(Comparator.comparing(Token::getSymbol));
            tokens.forEach(x -> x.setIndex(getPosition(x.getSymbol())));
        }
    }

    public int getPosition(String symbol){
        List<String> symbols = tokens.stream().map(Token::getSymbol).toList();
        if(symbols.contains(symbol))
            return symbols.indexOf(symbol);
        return -1;
    }

    public Token getToken(String symbol){
        return tokens.get(getPosition(symbol));
    }

    public Boolean find(String symbol){
        List<String> symbols = tokens.stream().map(Token::getSymbol).toList();
        return symbols.contains(symbol);
    }
}
