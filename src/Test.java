public class Test {
    private int id;
    private String name;
    private int dictId;

    public Test(int id, String name, int dictId) {
        this.id = id;
        this.name = name;
        this.dictId = dictId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDictId() {
        return dictId;
    }

    public void setDictId(int dictId) {
        this.dictId = dictId;
    }
}
