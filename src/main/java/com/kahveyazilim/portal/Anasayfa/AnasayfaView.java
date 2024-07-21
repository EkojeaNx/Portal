package com.kahveyazilim.portal.Anasayfa;

import com.kahveyazilim.portal.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.PermitAll;

@PageTitle("Ana Sayfa")
@Route(value = "anasayfa", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@PermitAll
public class AnasayfaView extends VerticalLayout {
    public AnasayfaView() {
        add(
                new H1("Ana Sayfa")
        );
    }
}
