package Grafik;

public class ModelDataPeminjaman {

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public ModelDataPeminjaman(String month, double amount) {
        this.month = month;
        this.amount = amount;
    }

    public ModelDataPeminjaman() {
    }

    private String month;
    private double amount;
   
}
