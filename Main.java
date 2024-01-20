import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> participants = new ArrayList<>();

        System.out.println("Enter names for Secret Santa (type 'done' to finish):");
        while (true) {
            String input = scanner.nextLine();
            if ("done".equalsIgnoreCase(input)) {
                break;
            }
            participants.add(input.trim());
        }

        if (participants.size() < 2) {
            System.out.println("At least two names are required for Secret Santa.");
            return;
        }

        List<String> receivers = new ArrayList<>(participants);
        Collections.shuffle(receivers);

        for (int i = 0; i < participants.size(); i++) {
            String giver = participants.get(i);
            String receiver = receivers.get(i);

            // Ensure a person does not get matched with themselves
            if (giver.equals(receiver)) {
                // Handle the special case where the last person gets themselves
                if (i == participants.size() - 1) {
                    // Swap the last person with someone else's receiver
                    String temp = receivers.get(0);
                    receivers.set(0, receiver);
                    receivers.set(i, temp);
                    receiver = temp;
                } else {
                    // Swap with the next person
                    String temp = receivers.get(i + 1);
                    receivers.set(i + 1, receiver);
                    receivers.set(i, temp);
                    receiver = temp;
                }
            }

            System.out.println(giver + " -> " + receiver);
        }
    }
}
