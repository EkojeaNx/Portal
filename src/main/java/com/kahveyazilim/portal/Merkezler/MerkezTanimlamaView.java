package com.kahveyazilim.portal.Merkezler;

import com.kahveyazilim.portal.Ayarlar.IController;
import com.kahveyazilim.portal.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Merkez Tanımlama")
@Route(value = "merkeztanimlama", layout = MainLayout.class)
@PermitAll
public class MerkezTanimlamaView extends VerticalLayout {

    Grid<Merkez> merkezTablo = new Grid<>();
    List<Merkez> merkezListesi = new ArrayList<>();
    IController<Merkez> merkezController;
    Button guncelleMerkezBTN = new Button(new Icon(VaadinIcon.REFRESH));
    Button kolonGizleGosterMerkez = new Button(new Icon(VaadinIcon.EYE));

    public MerkezTanimlamaView(MerkezController merkezController) {

        this.merkezController = merkezController;

        merkezTabloDoldur();
        merkezTabloAyarlari();

        add(
                getHeader(),
                getBody()
        );
    }

    private HorizontalLayout getHeader() {
        HorizontalLayout header = new HorizontalLayout();

        header.add(
               guncelleMerkezBTN,
               kolonGizleGosterMerkez
        );

        return header;
    }

    private VerticalLayout getBody() {
        VerticalLayout body = new VerticalLayout();

        body.add(
                merkezTablo
        );

        return body;
    }

    private void merkezTabloAyarlari() {
        Grid.Column<Merkez> merkezRefNo = merkezTablo.addColumn(Merkez::getMerkezRefNo).setHeader("Merkez Ref No").setFooter("Toplam Kayıt: " + merkezListesi.size()).setSortable(true);
        Grid.Column<Merkez> merkezAdi = merkezTablo.addColumn(Merkez::getMerkezAdi).setHeader("Merkez Adı").setSortable(true);
        Grid.Column<Merkez> aktif = merkezTablo.addComponentColumn(Merkez -> createStatusIcon(Merkez.getAktif())).setHeader("Aktif").setSortable(true);

        kolonGizleGosterMerkez.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        ColumnToggleContextMenu columnToggleContextMenu = new ColumnToggleContextMenu(kolonGizleGosterMerkez);
        columnToggleContextMenu.addColumnToggleItem("Merkez Ref No", merkezRefNo);
        columnToggleContextMenu.addColumnToggleItem("Merkez Adı", merkezAdi);
        columnToggleContextMenu.addColumnToggleItem("Aktif", aktif);
    }

    private void merkezTabloDoldur() {
        merkezListesi = merkezController.arayarakListele(null);
        merkezTablo.setItems(merkezListesi);
    }

    private static class ColumnToggleContextMenu extends ContextMenu {
        public ColumnToggleContextMenu(Component target) {
            super(target);
            setOpenOnClick(true);
        }


        void addColumnToggleItem(String label, Grid.Column<Merkez> column) {
            MenuItem menuItem = this.addItem(label, e -> {
                column.setVisible(e.getSource().isChecked());
            });
            menuItem.setCheckable(true);
            menuItem.setChecked(column.isVisible());
            menuItem.setKeepOpen(true);
        }
    }

    private Icon createStatusIcon(String status) {
        boolean isAvailable = "T".equals(status);
        Icon icon;
        if (isAvailable) {
            icon = VaadinIcon.CHECK.create();
            icon.getElement().getThemeList().add("badge success");
        } else {
            icon = VaadinIcon.CLOSE_SMALL.create();
            icon.getElement().getThemeList().add("badge error");
        }
        icon.getStyle().set("padding", "var(--lumo-space-xs");
        return icon;
    }
}
