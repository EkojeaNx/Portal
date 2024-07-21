package com.kahveyazilim.portal.Merkezler;

import com.kahveyazilim.portal.Ayarlar.IEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "merkez")
public class Merkez implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MerkezRefNo;
    private String MerkezAdi;
    private String aktif;
}
