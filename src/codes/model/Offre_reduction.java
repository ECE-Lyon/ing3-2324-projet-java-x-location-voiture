package codes.model;

public class Offre_reduction {


    private int id_offre_reduction;
    private float remise;

    public Offre_reduction(int id_offre_reduction, float remise){
        this.id_offre_reduction = id_offre_reduction;
        this.remise = remise;
    }

    public int getId_offre_reduction() {
        return id_offre_reduction;
    }

    public void setId_offre_reduction(int id_offre_reduction) {
        this.id_offre_reduction = id_offre_reduction;
    }

    public float getRemise() {
        return remise;
    }

    public void setRemise(float remise) {
        this.remise = remise;
    }
}