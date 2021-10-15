import java.util.*;

public class SortedTableSymbolTable {
    List<String> tokens;

    public SortedTableSymbolTable() {
        tokens = new ArrayList<>();
    }

    public Boolean add(String symbol){
        if(!tokens.contains(symbol)){
            tokens.add(symbol);
            Collections.sort(tokens);
            return true;
        }
        else
            return false;
    }

    public int getPosition(String symbol){
        if(tokens.contains(symbol))
            return tokens.indexOf(symbol);
        return -1;
    }
}
