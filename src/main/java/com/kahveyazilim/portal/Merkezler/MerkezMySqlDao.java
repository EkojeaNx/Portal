package com.kahveyazilim.portal.Merkezler;

import com.kahveyazilim.portal.Ayarlar.IRepositoryDao;
import com.kahveyazilim.portal.Ayarlar.VeritabaniBaglantiAyari;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MerkezMySqlDao implements IRepositoryDao<Merkez> {

    @Override
    public List<Merkez> listele() {
        ArrayList<Merkez> merkezListesi = new ArrayList<>();
        String sorgu = "SELECT * FROM merkez";
        Statement statement = null;
        ResultSet resultSet = null;
        Merkez merkez;

        try {
            statement = VeritabaniBaglantiAyari.baglan().createStatement();
            resultSet = statement.executeQuery(sorgu);

            while (resultSet.next()) {
                merkez = new Merkez(
                        resultSet.getInt("merkezrefno"),
                        resultSet.getString("merkezadi"),
                        resultSet.getString("aktif")
                );

                merkezListesi.add(merkez);
            }

            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return merkezListesi;
    }

    @Override
    public List<Merkez> arayarakListele(String aranacakKelime) {
        ArrayList<Merkez> merkezListesi = new ArrayList<>();
        String sorgu = "SELECT * FROM merkez WHERE merkezadi LIKE ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Merkez merkez;

        try {
            preparedStatement = VeritabaniBaglantiAyari.baglan().prepareStatement(sorgu);
            preparedStatement.setString(0, "%" + aranacakKelime + "%");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                merkez = new Merkez(
                        resultSet.getInt("merkezrefno"),
                        resultSet.getString("merkezadi"),
                        resultSet.getString("aktif")
                );

                merkezListesi.add(merkez);
            }

            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return merkezListesi;
    }

    @Override
    public boolean ekle(Merkez merkez) {
        boolean durum = false;
        String sorgu = "INSERT INTO merkez(merkezadi, aktif) VALUES (?, ?)";
        PreparedStatement preparedStatement = null;
        int sonuc;

        try {
            preparedStatement = VeritabaniBaglantiAyari.baglan().prepareStatement(sorgu);
            preparedStatement.setString(0, merkez.getMerkezAdi());
            preparedStatement.setString(1, merkez.getAktif());

            sonuc = preparedStatement.executeUpdate();

            if (sonuc != -1)
                durum = true;
            else
                durum = false;

            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return durum;
    }

    @Override
    public boolean guncelle(Merkez merkez) {
        boolean durum = false;
        String sorgu = "UPDATE merkez SET merkezadi = ?, aktif = ? WHERE merkezrefno = ?";
        PreparedStatement preparedStatement = null;
        int sonuc;

        try {
            preparedStatement = VeritabaniBaglantiAyari.baglan().prepareStatement(sorgu);
            preparedStatement.setString(0, merkez.getMerkezAdi());
            preparedStatement.setString(1, merkez.getAktif());
            preparedStatement.setInt(2, merkez.getMerkezRefNo());

            sonuc = preparedStatement.executeUpdate();

            if (sonuc != -1)
                durum = true;
            else
                durum = false;

            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return durum;
    }

    @Override
    public boolean sil(Merkez merkez) {
        boolean durum = false;
        String sorgu = "DELETE FROM merkez WHERE merkezrefno = ?";
        PreparedStatement preparedStatement = null;
        int sonuc;

        try {
            preparedStatement = VeritabaniBaglantiAyari.baglan().prepareStatement(sorgu);
            preparedStatement.setInt(0, merkez.getMerkezRefNo());

            sonuc = preparedStatement.executeUpdate();

            if (sonuc != -1)
                durum = true;
            else
                durum = false;

            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return durum;
    }
}
