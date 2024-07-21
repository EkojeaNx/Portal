package com.kahveyazilim.portal.Ayarlar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class VeritabaniBaglantiAyari {
    private Connection connection = null;

    private Connection veritabaniBaglan() {
        try {
            this.connection = DriverManager.getConnection(TemelAyarlar.VERITABANI_LINKI, TemelAyarlar.VERITABANI_KULLANICI_ADI, TemelAyarlar.VERITABANI_KULLANICI_PAROLA);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return this.connection;
    }

    public static Connection baglan() {
        VeritabaniBaglantiAyari veritabani = new VeritabaniBaglantiAyari();
        return veritabani.veritabaniBaglan();
    }
}
