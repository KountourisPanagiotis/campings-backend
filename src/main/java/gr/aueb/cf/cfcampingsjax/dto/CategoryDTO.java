package gr.aueb.cf.cfcampingsjax.dto;

public class CategoryDTO {
    private String catCode;
    private int areaM2;
    private float unitCost;

    public CategoryDTO() {}

    public CategoryDTO(String catCode, int areaM2, float unitCost) {
        this.catCode = catCode;
        this.areaM2 = areaM2;
        this.unitCost = unitCost;
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
}
