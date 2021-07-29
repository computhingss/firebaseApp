package com.tilda.denemeandroidapp;

public class Urun {
    private String stokKodu;
    private String urunAdi;
    private double fiyat;

    public Urun() {
    }

    public Urun(String stokKodu, String urunAdi, double fiyat) {
        this.stokKodu = stokKodu;
        this.urunAdi = urunAdi;
        this.fiyat = fiyat;
    }

    public String getStokKodu() {
        return stokKodu;
    }

    public void setStokKodu(String stokKodu) {
        this.stokKodu = stokKodu;
    }

    public String getUrunAdi() {
        return urunAdi;
    }

    public void setUrunAdi(String urunAdi) {
        this.urunAdi = urunAdi;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    @Override
    public String toString() {
        return "Urun{" +
                "stokKodu='" + stokKodu + '\'' +
                ", urunAdi='" + urunAdi + '\'' +
                ", fiyat=" + fiyat +
                '}';
    }
}
