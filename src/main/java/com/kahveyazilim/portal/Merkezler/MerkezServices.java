package com.kahveyazilim.portal.Merkezler;

import com.kahveyazilim.portal.Ayarlar.IRepositoryDao;
import com.kahveyazilim.portal.Ayarlar.IServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerkezServices implements IServices<Merkez> {

    private final IRepositoryDao<Merkez> merkezRepositoryDao;

    public MerkezServices(IRepositoryDao<Merkez> repositoryDao) {
        this.merkezRepositoryDao = repositoryDao;
    }

    @Override
    public List<Merkez> arayarakListele(String aranacakKelime) {
        if (aranacakKelime != null)
            return merkezRepositoryDao.arayarakListele(aranacakKelime);
        else
            return merkezRepositoryDao.listele();
    }

    @Override
    public boolean ekle(Merkez merkez) {
        return merkezRepositoryDao.ekle(merkez);
    }

    @Override
    public boolean guncelle(Merkez merkez) {
        return merkezRepositoryDao.guncelle(merkez);
    }

    @Override
    public boolean sil(Merkez merkez) {
        return merkezRepositoryDao.sil(merkez);
    }
}
