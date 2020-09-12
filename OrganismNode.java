/**
 * @author Calvin Lam
 * 112763367
 * CSE 214-R07
 *
 * This is the OrganismNode class which is used to represent an organism in the food pyramid
 */

public class OrganismNode {
    private String name;
    private boolean isPlant;
    private boolean isHerbivore;
    private boolean isCarnivore;
    private OrganismNode parent;
    private OrganismNode left;
    private OrganismNode middle;
    private OrganismNode right;
    private int depth;

    /**
     * This is the constructor for OrganismNode
     */
    public OrganismNode(){
        depth=0;
    }

    /**
     * This is the getter method for name
     * @return name of organism
     */
    public String getName() {
        return name;
    }

    /**
     * This is the getter method for isPlant
     * @return true or false based on if organism is a plant
     */
    public boolean isPlant() {
        return isPlant;
    }

    /**
     * This is the getter method for isHerbivore
     * @return true or false based on if organism is a herbivore
     */
    public boolean isHerbivore() {
        return isHerbivore;
    }

    /**
     * This is the getter method for isCarnivore
     * @return true or false based on if organism is a carnivore
     */
    public boolean isCarnivore() {
        return isCarnivore;
    }

    /**
     * This is the getter method for left
     * @return OrganismNode connected to the left of original organism
     */
    public OrganismNode getLeft() {
        return left;
    }
    /**
     * This is the getter method for middle
     * @return OrganismNode connected to the middle of original organism
     */
    public OrganismNode getMiddle() {
        return middle;
    }
    /**
     * This is the getter method for right
     * @return OrganismNode connected to the right of original organism
     */
    public OrganismNode getRight() {
        return right;
    }

    /**
     * This is the setter method for name
     * @param name name of organism
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This is the setter method for plant
     * @param plant true if organism is a plant
     */
    public void setPlant(boolean plant) {
        isPlant = plant;
    }

    /**
     * This is the setter method for herbivore
     * @param herbivore true if organism is a herbivore
     */
    public void setHerbivore(boolean herbivore) {
        isHerbivore = herbivore;
    }

    /**
     * This is the setter method for carnivore
     * @param carnivore true if organism is a carnivore
     */
    public void setCarnivore(boolean carnivore) {
        isCarnivore = carnivore;
    }

    /**
     * This is the setter method for left
     * @param left links OrganismNode to left
     */
    public void setLeft(OrganismNode left) {
        this.left = left;
    }
    /**
     * This is the setter method for middle
     * @param middle links OrganismNode to middle
     */
    public void setMiddle(OrganismNode middle) {
        this.middle = middle;
    }
    /**
     * This is the setter method for right
     * @param right links OrganismNode to right
     */
    public void setRight(OrganismNode right) {
        this.right = right;
    }

    /**
     * This is the getter method for parent
     * @return OrganismNode linked above
     */
    public OrganismNode getParent() {
        return parent;
    }

    /**
     * This is the setter method for parent
     * @param parent sets OrganismNode link from above
     */
    public void setParent(OrganismNode parent) {
        this.parent = parent;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * This method adds prey to OrganismNode and sets links of left, middle, and right
     * @param preyNode name of prey OrganismNode
     */
    public void addPrey(OrganismNode preyNode){
        if(isPlant()==true)
            throw new IllegalArgumentException("isPlantException");
        if(getRight()!=null)
            throw new IllegalArgumentException("PositionNotAvailableException");
        if(isHerbivore()==true && isCarnivore()==false && preyNode.isPlant==false)
           throw new IllegalArgumentException("DietMismatchException");
        if(isHerbivore()==false && isCarnivore()==true && preyNode.isPlant==true)
            throw new IllegalArgumentException("DietMismatchException");
        if(getLeft()==null)
            setLeft(preyNode);
        else if(getMiddle()==null)
            setMiddle(preyNode);
        else if(getRight()==null)
            setRight(preyNode);
        preyNode.setDepth(getDepth()+1);
    }

    /**
     * This is the toString method of OrganismNode
     * @return String storing an OrganismNode and its prey
     */
    @Override
    public String toString() {
        if(getRight()!=null)
            return name + " -> " + getLeft().getName() + ", " + getMiddle().getName() + ", " + getRight().getName();
        else if(getMiddle()!=null)
            return name + " -> " + getLeft().getName() + ", " + getMiddle().getName();
        else if(getLeft()!=null)
            return name + " -> " + getLeft().getName();
        else
            return name;
    }
}
