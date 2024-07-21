package com.kahveyazilim.portal.Ayarlar;

import java.util.List;

public interface IServices<T extends IEntity> {
    public List<T> arayarakListele(String aranacakKelime);
    public boolean ekle(T model);
    public boolean guncelle(T model);
    public boolean sil(T model);
}
