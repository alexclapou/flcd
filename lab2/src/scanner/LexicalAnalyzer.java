package scanner;

import symboltable.SortedTableSymbolTable;
import symboltable.Token;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LexicalAnalyzer {
    String delimiters = "[]{}()+-*/%,;>=<!^ ";
    List<Token> PIF = new ArrayList<>();
    SortedTableSymbolTable symbolTable = new SortedTableSymbolTable();
    List<String> reservedWords;

    public LexicalAnalyzer(){
        reservedWords = readFromFile("src/reserved.txt");
    }

    public void Scan(String programName){
        boolean lexicallyCorrect = true;
        String programFile = "src/" + programName + ".txt";
        List<String> program;
        program = readFromFile(programFile);
        int lineNumber = 1;
        for (String line : program) {
            //split the line and add the tokens to a list of tokens
            //detect tokens
            StringTokenizer st = new StringTokenizer(line, delimiters, true);
            List<String> tokens = new ArrayList<>();
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                if (!token.equals(" ")) {
                    tokens.add(token);
                }
            }

            boolean found = false;
            int keep_index = 0;
            for(int i = 0; i < tokens.size(); i++){
                if(found){
                    String newString = tokens.get(keep_index) + ' ' + tokens.get(i);
                    if(tokens.get(i).trim().charAt(tokens.get(i).trim().length()-1) == '"'){
                        found = false;
                    }
                    tokens.remove(i);
                    i--;
                    tokens.set(keep_index, newString);
                    continue;
                }
                if(tokens.get(i).charAt(0) == '"'){
                    found = true;
                    keep_index = i;
                }
            }

            //check if the token is reserved word/operator/separator/constant/identifier
            for(String token: tokens){
                //check if is a reserved word
                if(reservedWords.contains(token)){
                    Token reserved = new Token(token);
                    reserved.setIndex(-1);
                    PIF.add(reserved);
                }
                else{
                    //check if is a identifier or constant
                    if(isIdentifierOrConstant(token)){
                        if(symbolTable.find(token)){
                            PIF.add(symbolTable.getToken(token));
                        }
                        else{
                            Token newToken = new Token(token);
                            symbolTable.add(newToken);
                            PIF.add(newToken);
                        }
                    }
                    //lexical error here
                    else{
                        lexicallyCorrect = false;
                        System.out.println("LEXICAL ERROR ON LINE " + lineNumber);
                    }
                }
            }
            lineNumber++;
        }
        if(lexicallyCorrect)
            System.out.println("LEXICALLY CORRECT");
        writeToFile("src/"+programName+"/pif", PIF);
        writeToFile("src/"+programName+"/st", symbolTable.getTokens());
    }

    public Boolean isIdentifierOrConstant(String token) {
        // matches identifier
        if (token.matches("^[a-zA-Z_]([a-zA-Z0-9_])*")) {
            return true;
        }
        //matches integer
        if (token.matches("^([+-])?([1-9][0-9]*)|0")) {
            return true;
        }
        //matches boolean
        if (token.matches("true|false")) {
            return true;
        }
        //matches char
        if (token.matches("^'[a-zA-Z0-9_~`!@#$%^&*()_ +-{}]'$")) {
            return true;
        }
        //matches string
        if (token.matches("^\"([a-zA-Z0-9 _])*+\"$")){
            return true;
        }
        return false;
    }

    public List<String> readFromFile(String fileName){
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    public void writeToFile(String fileName, List<Token> tokens){
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))){
            for(var token: tokens)
                writer.println(token);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
