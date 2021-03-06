package fa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FiniteAutomata {
    Set<String> states;
    Set<String> inputs;
    String initialState;
    Set<String> finalStates;
    Map<Pair<String, String>, String> transitions;
    boolean isDeterministic = true;

    public FiniteAutomata(Set<String> states, Set<String> inputs, String initialState,
                          Set<String> finalStates, Map<Pair<String, String>, String> transitions) {
        this.states = states;
        this.inputs = inputs;
        this.initialState = initialState;
        this.finalStates = finalStates;
        this.transitions = transitions;
    }

    public FiniteAutomata(){
        states = new HashSet<>();
        inputs = new HashSet<>();
        initialState = "";
        finalStates = new HashSet<>();
        transitions = new HashMap<>();
        ReadFromFile();
    }

    public void runMenu() throws IOException{
        Scanner in = new Scanner(System.in);
        int selected;
        while(true){
            System.out.print("-------------------------\n");
            System.out.println("1 - set of all states");
            System.out.println("2 - inputs");
            System.out.println("3 - initial state");
            System.out.println("4 - set of final states");
            System.out.println("5 - transitions");
            System.out.println("6 - verify if accepted");
            System.out.println("7 - quit");
            System.out.print("> ");
            selected = in.nextInt();
            if(selected == 1){
                System.out.print("set of all states: ");
                System.out.println(states);
            }
            if(selected == 2){
                System.out.print("inputs: ");
                System.out.println(inputs);
            }
            if(selected == 3){
                System.out.print("initial state: ");
                System.out.println(initialState);
            }
            if(selected == 4){
                System.out.print("set of final states: ");
                System.out.println(finalStates);
            }
            if(selected == 5){
                System.out.print("transitions: ");
                System.out.println(transitions);
            }
            if(selected == 6){
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in));
                String sequence[] = reader.readLine().split(" ");
                String actualState = initialState;
                boolean ok = true;
                for(var character: sequence){
                    if(!inputs.contains(character)){
                        System.out.println("the alphabet doesn't contain an element");
                        ok = false;
                        break;
                    }
                    else{
                        if(transitions.containsKey(new Pair<>(actualState, character)))
                            actualState = transitions.get(new Pair<>(actualState, character));
                        else {
                            ok = false;
                            break;
                        }
                    }
                }
                if(ok)
                    System.out.println("OK");
                else
                    System.out.println("NOT OK");
            }
            if(selected == 7) {
                break;
            }
        }
    }

    private void ReadFromFile(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "src/fa.txt"));
            String line = reader.readLine();
            //set of all states
            Collections.addAll(states, line.split(" "));

            line = reader.readLine();

            //inputs
            Collections.addAll(inputs, line.split(" "));

            line = reader.readLine();

            //initial state
            initialState = line;

            line = reader.readLine();

            //set of final states
            Collections.addAll(finalStates, line.split(" "));

            line = reader.readLine();
            while(line != null) {
                List<String> transition = List.of(line.split(" "));
                if(transitions.containsKey(new Pair<>(transition.get(0), transition.get(1)))) {
                    isDeterministic = false;
                }
                transitions.put(new Pair<>(transition.get(0), transition.get(1)), transition.get(2));
                line = reader.readLine();
            }
            /*
            System.out.print("set of all states: ");
            System.out.println(states);
            System.out.print("inputs: ");
            System.out.println(inputs);
            System.out.print("initial state: ");
            System.out.println(initialState);
            System.out.print("set of final states: ");
            System.out.println(finalStates);
            System.out.print("transitions: ");
            System.out.println(transitions);
             */
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
