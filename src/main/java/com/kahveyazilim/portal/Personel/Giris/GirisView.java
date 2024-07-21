package com.kahveyazilim.portal.Personel.Giris;

import com.kahveyazilim.portal.Ayarlar.TemelAyarlar;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;

@PageTitle("Personel Giriş")
@Route(value = "giris")
@AnonymousAllowed
public class GirisView extends VerticalLayout {
    public GirisView() {
        // Giriş Formu
        LoginOverlay girisForm = new LoginOverlay();

        // i18n Tasarımı
        LoginI18n i18n = LoginI18n.createDefault();

        // Header
        LoginI18n.Header header = new LoginI18n.Header();
        header.setTitle(TemelAyarlar.PROJE_KISA_ADI.toUpperCase());
        header.setDescription(TemelAyarlar.PROJE_ACIKLAMA);
        i18n.setHeader(header);

        // Body
        LoginI18n.Form form = i18n.getForm();
        form.setTitle("Personel Giriş Paneli");
        form.setUsername("Personel Kodu");
        form.setPassword("Parola");
        form.setSubmit("Giriş Yap");
        form.setForgotPassword("Parolamı Unuttum!");
        i18n.setForm(form);

        // Footer
        Paragraph footerTitle = new Paragraph(TemelAyarlar.SIRKET_ADI_OZEL);
        footerTitle.addClassName(LumoUtility.TextAlignment.CENTER);
        girisForm.getFooter().add(footerTitle);

        // Error Message
        LoginI18n.ErrorMessage errorMessage = i18n.getErrorMessage();
        errorMessage.setTitle("");
        errorMessage.setMessage("");
        errorMessage.setUsername("Personel Kodu boş bırakılamaz!");
        errorMessage.setPassword("Parola boş bırakılamaz!");
        i18n.setErrorMessage(errorMessage);

        // Tasarımı Forma Ekleme
        girisForm.setI18n(i18n);

        // Form Ayarları
        girisForm.setAction("login");
        girisForm.setOpened(true);

        add(
                girisForm
        );
    }
}
