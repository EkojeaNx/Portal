package com.kahveyazilim.portal.Merkezler;

import com.kahveyazilim.portal.Ayarlar.IController;
import com.kahveyazilim.portal.Ayarlar.IServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/merkez")
public class MerkezController implements IController<Merkez> {

    private final IServices<Merkez> merkezServices;

    public MerkezController() {
        this.merkezServices = new MerkezServices(new MerkezMySqlDao());
    }

    @GetMapping
    @Override
    public List<Merkez> arayarakListele(String aranacakKelime) {
        return merkezServices.arayarakListele(aranacakKelime);
    }

    @PostMapping
    @Override
    public boolean ekle(@RequestBody Merkez merkez) {
        return merkezServices.ekle(merkez);
    }

    @PutMapping
    @Override
    public boolean guncelle(@RequestBody Merkez merkez) {
        return merkezServices.guncelle(merkez);
    }

    @DeleteMapping
    @Override
    public boolean sil(@RequestBody Merkez merkez) {
        return merkezServices.sil(merkez);
    }
}
