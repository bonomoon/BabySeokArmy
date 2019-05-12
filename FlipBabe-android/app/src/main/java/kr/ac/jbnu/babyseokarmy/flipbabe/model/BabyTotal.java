package kr.ac.jbnu.babyseokarmy.flipbabe.model;

public class BabyTotal {
    int flipTotal;
    int peeTotal;
    int pooTotal;

    public BabyTotal() {
    }

    public BabyTotal(int flipTotal, int peeTotal, int pooTotal) {
        this.flipTotal = flipTotal;
        this.peeTotal = peeTotal;
        this.pooTotal = pooTotal;
    }

    public int getFlipTotal() {
        return flipTotal;
    }

    public void setFlipTotal(int flipTotal) {
        this.flipTotal = flipTotal;
    }

    public int getPeeTotal() {
        return peeTotal;
    }

    public void setPeeTotal(int peeTotal) {
        this.peeTotal = peeTotal;
    }

    public int getPooTotal() {
        return pooTotal;
    }

    public void setPooTotal(int pooTotal) {
        this.pooTotal = pooTotal;
    }
}