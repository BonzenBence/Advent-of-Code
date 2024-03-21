public class StringWithPair {
    private String Name;
    private StringPair pair;

    public StringWithPair(String Name,StringPair pair){
        this.Name = Name;
        this.pair = pair;
    }

    public StringPair getPair() {
        return pair;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPair(StringPair pair) {
        this.pair = pair;
    }
}
