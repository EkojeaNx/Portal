package com.kahveyazilim.portal.Personel;

import com.kahveyazilim.portal.Ayarlar.IEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personel")
public class Personel implements IEntity {
    @Id
    private String PersonelKodu;
    private String Adi;
    private String Soyadi;

}
