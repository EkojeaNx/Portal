package com.kahveyazilim.portal;

import com.kahveyazilim.portal.Anasayfa.AnasayfaView;
import com.kahveyazilim.portal.Ayarlar.TemelAyarlar;
import com.kahveyazilim.portal.Merkezler.MerkezTanimlamaView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.component.contextmenu.HasMenuItems;
import com.vaadin.flow.component.Text;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H1 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.AUTO);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        Span appName = new Span(TemelAyarlar.PROJE_ADI.toUpperCase());
        appName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.XLARGE);
        Header header = new Header(
                new Image(TemelAyarlar.PROJE_ICON_LINKI, TemelAyarlar.PROJE_KISA_ADI),
                appName
        );

        MenuBar menuBar = new MenuBar();
        menuBar.addThemeVariants(MenuBarVariant.LUMO_ICON);
        menuBar.addClassNames(LumoUtility.AlignItems.CENTER);
        MenuItem kullaniciMenu = createIconItem(menuBar, VaadinIcon.USER, "EkojeaN", null);
        SubMenu kullaniciSubMenu = kullaniciMenu.getSubMenu();
        createIconItem(kullaniciSubMenu, VaadinIcon.COG, "Oturumu Kapat", null, true);

        Header header2 = new Header(menuBar);
        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(
                header,
                new Hr(),
                header2,
                new Hr(),
                scroller,
                new Hr(),
                createFooter()
        );
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();
        SideNavItem anasayfaMenu = new SideNavItem("Ana Sayfa", AnasayfaView.class, VaadinIcon.HOME.create());
        SideNavItem tanimlamalarMenu = new SideNavItem("Tanımlamalar");
        tanimlamalarMenu.setPrefixComponent(VaadinIcon.COG.create());
        tanimlamalarMenu.addItem(new SideNavItem("Merkez Tanımlama", MerkezTanimlamaView.class, VaadinIcon.CALENDAR_ENVELOPE.create()));

        nav.addItem(
                anasayfaMenu,
                tanimlamalarMenu
        );

        return nav;
    }

    private MenuItem createIconItem(HasMenuItems menu, VaadinIcon iconName,
                                    String label, String ariaLabel) {
        return createIconItem(menu, iconName, label, ariaLabel, false);
    }

    private MenuItem createIconItem(HasMenuItems menu, VaadinIcon iconName,
                                    String label, String ariaLabel, boolean isChild) {
        Icon icon = new Icon(iconName);

        if (isChild) {
            icon.getStyle().set("width", "var(--lumo-icon-size-s)");
            icon.getStyle().set("height", "var(--lumo-icon-size-s)");
            icon.getStyle().set("marginRight", "var(--lumo-space-s)");
        }

        MenuItem item = menu.addItem(icon, e -> {
        });

        if (ariaLabel != null) {
            item.setAriaLabel(ariaLabel);
        }

        if (label != null) {
            item.add(new Text(label));
        }

        return item;
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        Span companyName = new Span(TemelAyarlar.SIRKET_ADI_OZEL);
        companyName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);
        layout.add(
                companyName
        );
        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
