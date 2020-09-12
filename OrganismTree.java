/**
 * @author Calvin Lam
 * 112763367
 * CSE 214-R07
 *
 * This is the OrganismTree class which links together OrganismNodes based on diet
 */

public class OrganismTree {
    private OrganismNode root;
    private OrganismNode cursor;
    private String plants;
    //private int counter;

    /**
     * This is the constructor for OrganismTree
     * @param apexPredator name of OrganismNode and root of tree
     */
    public OrganismTree(OrganismNode apexPredator){
        root=apexPredator;
        cursor=root;
        plants="";

      //  counter=1;
    }

    /**
     * This method resets the cursor back to the root
     */
    public void cursorReset(){
        cursor=root;
    }

    /**
     * This method moves the cursor from an OrganismNode to one of its children
     * @param name name of child
     */
    public void moveCursor(String name){
        if(cursor.getLeft().getName().equals(name))
            cursor=cursor.getLeft();
        else if(cursor.getMiddle().getName().equals(name))
            cursor=cursor.getMiddle();
        else if(cursor.getRight().getName().equals(name))
            cursor=cursor.getRight();
        else
            throw new IllegalArgumentException("child name not found");
    }

    /**
     * This method returns a string representation of an organisms prey
     * @return String storing OrganismNode and its prey
     */
    public String listPrey(){
        String list=cursor.toString();
        return list;
    }

    /**
     * This method lists all animals linked between the root and cursor
     * @return String representation of all organisms linked between root and cursor
     */
    public String listFoodChain(){
        String list="";
        OrganismNode temp=cursor;
        if(root==cursor) {
            list = root.getName() + list;
            return list;
        }
        list=cursor.getName();
        cursor=cursor.getParent();
        while(cursor!=root) {
            list=cursor.getName()+"->"+list;
            cursor = cursor.getParent();
        }
        list=cursor.getName()+"->"+list;
        cursor=temp;
        return list;
    }

    /**
     * This method prints the OrganismTree starting from the cursor, which displays all OrganismNodes and their food sources
     */
    public void printOrganismTree(){
        OrganismNode temp=cursor;
        printPreorder(cursor);
        cursor=temp;

    }

    /**
     * This is the helper method for printOrganismTree
     * @param cursor OrganismNode being referenced by cursor
     */
    public void printPreorder(OrganismNode cursor){
        if(cursor==null)
            return;
        for(int i=0; i<cursor.getDepth();i++) {
            System.out.print("\t");
        }
        if(cursor.isPlant()==false)
        System.out.print("| - " +cursor.getName());
        else
            System.out.print(" - " +cursor.getName());
        System.out.print("\n");
        printPreorder(cursor.getLeft());
        printPreorder(cursor.getMiddle());
        printPreorder(cursor.getRight());
    }

    /**
     * This method returns a string storing all plant names
     * @return string storing all plant names
     */
    public String listAllPlants(){
        OrganismNode temp=cursor;
        setPlants("");
        lapHelper(cursor);
        cursor=temp;
        return getPlants();
    }

    /**
     * This is the helper method for listAllPlants()
     * @param cursor OrganismNode referenced by cursor
     */
    public void lapHelper(OrganismNode cursor){
        if(cursor==null)
            return;
        if(cursor.isPlant()==true)
            setPlants(getPlants() +cursor.getName()+", ");
        lapHelper(cursor.getLeft());
        lapHelper(cursor.getMiddle());
        lapHelper(cursor.getRight());
    }

    /**
     * This is the addAnimalChild method which adds and links a child to an OrganismNode
     * @param name name of organism
     * @param isHerbivore true or false based on if organism is a herbivore
     * @param isCarnivore true or false based on if organism is a carnivore
     */
    public void addAnimalChild(String name, boolean isHerbivore, boolean isCarnivore){
        OrganismNode child= new OrganismNode();
        child.setName(name);
        if(isHerbivore==true)
            child.setHerbivore(true);
        if(isCarnivore==true)
            child.setCarnivore(true);
        child.setParent(cursor);
        cursor.addPrey(child);

    }

    /**
     * This is the addPlantChild which adds and links a plant as a child of organism
     * @param name name of plant
     */
    public void addPlantChild(String name){
        OrganismNode child= new OrganismNode();
        child.setName(name);
        child.setPlant(true);
        try {
            cursor.addPrey(child);
            child.setParent(cursor);
        }catch (Exception IllegalArgumentException){
            System.out.println("isPlantException");
        }
    }

    /**
     * This is the removeChild method which removes inputted child name
     * @param name name of child
     */
    public void removeChild(String name){
        if(root.getLeft().getName().equals(name)) {
            root.setLeft(null);
            if(root.getMiddle()!=null) {
                root.setLeft(root.getMiddle());
                root.setMiddle(null);
                if (root.getRight() != null) {
                    root.setMiddle(root.getRight());
                    root.setRight(null);
                }
            }
        }
        else if(root.getMiddle().getName().equals(name)) {
            root.setMiddle(null);
            if(root.getRight()!=null) {
                root.setMiddle(root.getRight());
                root.setRight(null);
            }
        }
        else if(root.getRight().getName().equals(name))
            root.setRight(null);
        else
            throw new IllegalArgumentException("name does not reference a direct child");
    }

    /**
     * This is the getter method for root
     * @return OrganismNode referenced by root
     */
    public OrganismNode getRoot() {
        return root;
    }

    /**
     * This is the getter method for cursor
     * @return OrganismNode referenced by cursor
     */
    public OrganismNode getCursor() {
        return cursor;
    }

    /**
     * This is the getter method for plants
     * @return string storing all plant names
     */
    public String getPlants() {
        return plants;
    }

    /**
     * This is the setter method for plants
     * @param plants list of all plant names
     */
    public void setPlants(String plants) {
        this.plants = plants;
    }
}
