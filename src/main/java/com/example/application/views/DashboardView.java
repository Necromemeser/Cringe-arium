package com.example.application.views;

import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "", layout = MainLayout.class) // <1>
@PageTitle("Реклама!")
public class DashboardView extends VerticalLayout {
    private final CrmService service;

    public DashboardView(CrmService service) { // <2>
        this.service = service;
//        setSizeFull();
        setSpacing(true);
        setPadding(true);

        // This VerticalLayout contains the header, contentDiv and button.
        VerticalLayout layout = new VerticalLayout();
        layout.getStyle().set("padding-left", "20%");
        layout.getStyle().set("padding-right", "20%");
        layout.getStyle().set("background-color", "#F5F5F5");
        layout.getStyle().set("flex-shrink", "0");
        layout.setHeight("50%");
        layout.setWidthFull();
        layout.setSpacing(false);

        H1 header = new H1("Добро пожаловать в Кринжариум!");
        header.setWidthFull();
        Div contentDiv = new Div();
        contentDiv.setText(
                "This is a template for a simple marketing or informational "
                        + "website. It includes three supporting pieces of "
                        + "content. Use it as a starting point to create "
                        + "something more unique.");
        Button button = new Button("Кнопка тут для прикола",
                new Icon(VaadinIcon.ANGLE_DOUBLE_RIGHT));
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        layout.add(header, contentDiv, button);

        // This HorizontalLayout contains box1, box2 and box3.
        HorizontalLayout container = new HorizontalLayout();
        container.setSpacing(false);
        container.getStyle().set("flex-wrap", "wrap");
        container.setSizeFull();
        container.getStyle().set("padding-left", "20%");
        container.getStyle().set("padding-right", "20%");

        String cardHeader = "Heading";
        String cardContent = "Donec id elit non mi porta gravida at eget"
                + " metus. Fusce dapibus, tellus ac cursus commodo, tortor"
                + " mauris condimentum nibh, ut fermentum massa justo sit amet "
                + "risus. Etiam porta sem malesuada magna mollis  euismod. "
                + "Donec sed odio dui.";

        Component card1 = createCard(cardHeader, cardContent);
        Component card2 = createCard(cardHeader, cardContent);
//        Component card3 = createCard(cardHeader, cardContent);

        container.add(card1, card2);

        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(layout, container, getContactStats(), getCompaniesChart());
        // old

         // <3>

//        add(getContactStats(), getCompaniesChart());
    }

    private Component getContactStats() {
        Span stats = new Span("С нами уже " + service.countContacts() + " ученик(-ов/-а!)"); // <4>
        stats.addClassNames(
            LumoUtility.FontSize.XLARGE,
            LumoUtility.Margin.Top.MEDIUM);
        return stats;
    }

    private Chart getCompaniesChart() {
        Chart chart = new Chart(ChartType.PIE);

        DataSeries dataSeries = new DataSeries();
        service.findAllGroups().forEach(group ->
            dataSeries.add(new DataSeriesItem(group.getName(), group.getStudentsCount()))); // <5>
        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }

    private Component createCard(String cardHeader, String cardContent) {
        VerticalLayout layout = new VerticalLayout();
        layout.setWidth("30%");
        layout.setMinWidth("250px");

        H2 header = new H2(cardHeader);
        Div content = new Div();
        content.setText(cardContent);

        Button button = new Button("View details",
                new Icon(VaadinIcon.ANGLE_DOUBLE_RIGHT));
        button.addThemeVariants(ButtonVariant.LUMO_SMALL);

        layout.getElement().getStyle().set("flex-grow", "1");
        layout.add(header, content, button);
        return layout;
    }
}