package com.kahveyazilim.portal;

import com.kahveyazilim.portal.Ayarlar.TemelAyarlar;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "portalistakipsistemi", variant = Lumo.DARK)
@PWA(
        name = TemelAyarlar.PROJE_ADI,
        shortName = TemelAyarlar.PROJE_KISA_ADI,
        description = TemelAyarlar.PROJE_ACIKLAMA,
        iconPath = TemelAyarlar.PROJE_ICON_LINKI,
        offlinePath = ""
)
public class Application implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
