package RetakeExam;

import java.util.*;
import java.util.stream.Collectors;

public class PastryShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> liquids = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> ingredients = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(ingredients::push);

        Map<String, Integer> delicacies = new LinkedHashMap<>();
        delicacies.put("Biscuit", 0);
        delicacies.put("Cake", 0);
        delicacies.put("Pie", 0);
        delicacies.put("Pastry", 0);

        while (!liquids.isEmpty() && !ingredients.isEmpty()) {
            int currentLiquid = liquids.poll();
            int currentIngredient = ingredients.peek();
            int sum = currentLiquid + currentIngredient;

            switch (sum) {
                case 25:
                    delicacies.put("Biscuit", delicacies.get("Biscuit") + 1);
                    ingredients.pop();
                    break;
                case 50:
                    delicacies.put("Cake", delicacies.get("Cake") + 1);
                    ingredients.pop();
                    break;
                case 75:
                    delicacies.put("Pastry", delicacies.get("Pastry") + 1);
                    ingredients.pop();
                    break;
                case 100:
                    delicacies.put("Pie", delicacies.get("Pie") + 1);
                    ingredients.pop();
                    break;
                default:
                    ingredients.pop();
                    ingredients.push(currentIngredient + 3);
            }
        }


        int sum = Arrays.stream(delicacies.values().toArray(new Integer[0])).mapToInt(Integer::intValue).sum();

        if (delicacies.get("Biscuit") > 0 && delicacies.get("Cake") > 0
        && delicacies.get("Pastry") > 0 && delicacies.get("Pie") > 0) {
            System.out.println("Great! You succeeded in cooking all the food!");
        } else {
            System.out.println("What a pity! You didn't have enough materials to cook everything.");
        }

        if (liquids.isEmpty()) {
            System.out.println("Liquids left: none");
        } else {
            System.out.println("Liquids left: " + liquids.stream().map(String::valueOf).collect(Collectors.joining(", ")));
        }

        if (ingredients.isEmpty()) {
            System.out.println("Ingredients left: none");
        } else {
            System.out.println("Ingredients left: " + ingredients.stream().map(String::valueOf).collect(Collectors.joining(", ")));
        }

        for (Map.Entry<String, Integer> entry : delicacies.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
