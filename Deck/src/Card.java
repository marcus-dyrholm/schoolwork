public class Card {
    public String type;
    public int name;
    public String colour;

    public Card() {
    }

    //Card Constructor
    public Card(String type, String colour) {
        this.type = type;
        this.colour = colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
