package gr.aueb.cf.cfcampingsjax.model;

public class Category {
    private String catCode;
    private int areaM2;
    private float unitCost;

    public Category() {}

    public Category(String catCode, int areaM2, float unitCost) {
        this.catCode = catCode;
        this.areaM2 = areaM2;
        this.unitCost = unitCost;
    }

    // Copy constructor
    public Category(Category other) {
        this.catCode = other.catCode;
        this.areaM2 = other.areaM2;
        this.unitCost = other.unitCost;
    }

    public String getCatCode() {
        return catCode;
    }

    public void setCatCode(String catCode) {
        this.catCode = catCode;
    }

    public int getAreaM2() {
        return areaM2;
    }

    public void setAreaM2(int areaM2) {
        this.areaM2 = areaM2;
    }

    public float getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(float unitCost) {
        this.unitCost = unitCost;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "catCode='" + catCode + '\'' +
                ", areaM2=" + areaM2 +
                ", unitCost=" + unitCost +
                '}';
    }
}
