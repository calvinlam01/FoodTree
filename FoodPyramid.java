/**
 * @author Calvin Lam
 * 112763367
 * CSE 214-R07
 *
 * This is the FoodPyramid class which is a virtual machine used for creating a food pyramid
 */

import java.util.Scanner;
public class FoodPyramid {
    public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            System.out.println("What is the name of the apex predator?: ");
            String name = scan.nextLine();
            System.out.println("Is the organism an herbivore / a carnivore / an omnivore (H / C / O): ");
            String diet = scan.nextLine();
            OrganismNode t = new OrganismNode();
            t.setName(name);
            OrganismTree tree = new OrganismTree(t);
            if (diet.equals("H"))
                t.setHerbivore(true);
            else if (diet.equals("C"))
                t.setCarnivore(true);
            else if (diet.equals("O")) {
                t.setHerbivore(true);
                t.setCarnivore(true);
            }
            while(true){
            System.out.println("(PC) - Create New Plant Child \n(AC) - Create New Animal Child \n(RC) - Remove Child \n(P)  - Print out Cursor's Prey \n(C)  - Print out Food Chain \n(F)  - Print Out Food Pyramid at Cursor \n(LP) - List All Plants Supporting Cursor \n(R)  - Reset Cursor to Root \n(M)  - Move Cursor to Child \n(Q)  - Quit");
            String input=scan.nextLine();
            if(input.equals("PC")){
                System.out.println("What is the name of the organism?: ");
                name=scan.nextLine();
                try {
                    tree.addPlantChild(name);
                    System.out.println(name + " has successfully been added as prey for the " + tree.getCursor().getName());
                }catch(Exception IllegalArgumentException) {
                    System.out.println("isPlantException");
                }
            }
            if(input.equals("AC")){
                System.out.println("What is the name of the organism?: ");
                name=scan.nextLine();
                System.out.println("Is the organism an herbivore / a carnivore / an omnivore (H / C / O): ");
                diet=scan.nextLine();
                try {
                    if (diet.equals("H"))
                        tree.addAnimalChild(name, true, false);
                    else if (diet.equals("C"))
                        tree.addAnimalChild(name, false, true);
                    else if (diet.equals("O")) {
                        tree.addAnimalChild(name, true, true);
                    }
                    System.out.println("A(n) " + name + " has successfully been added as prey for " + tree.getCursor().getName() +"!" );
                }catch(Exception IllegalArgumentException){
                    if(t.getRight()==null)
                        System.out.println("PositionNotAvailableException");
                    else if(t.isPlant())
                        System.out.println("isPlantException");
                    else
                        System.out.println("DietMismatchException");
                }
            }
            if(input.equals("RC")){
                System.out.println("What is the name of the organism?: ");
                String remove= scan.nextLine();
                try {
                    tree.removeChild(remove);
                }catch(Exception IllegalArgumentException){
                    System.out.println("No direct child with name");
                }
                System.out.println("A(n) " + remove + " has successfully been removed as prey for " + t.getName() +"!" );
            }
            if(input.equals("P")){
                System.out.println(tree.listPrey());
            }
            if(input.equals("C")){
                System.out.println(tree.listFoodChain());
            }
            if(input.equals("F")){
                tree.printOrganismTree();
            }
            if(input.equals("LP")){
                System.out.println(tree.listAllPlants());
            }
            if(input.equals("R")){
                tree.cursorReset();
                System.out.println("Cursor successfully reset to root!");
            }
            if(input.equals("M")){
                System.out.println("Move to?: ");
                name=scan.nextLine();
                try {
                    tree.moveCursor(name);
                }catch (Exception IllegalArgumentException){
                    System.out.println("Child name not found");
                }
                System.out.println("Cursor successfully moved to " + name);
            }
            if(input.equals("Q")){
                System.out.println("Program terminating....");
                System.exit(0);
            }
        }
    }
}
