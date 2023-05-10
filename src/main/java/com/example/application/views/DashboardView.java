package com.example.application.views;

import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.cookieconsent.CookieConsent;
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
                "Лучшая школа рофляных языков на просторах " +
                "всего Интернета. Не стесняйтесь - присоединяйтесь! " +
                "Пожажуста, мне нужны деньги fr...");
        Button button = new Button("Кнопка тут для прикола",
                new Icon(VaadinIcon.ANGLE_DOUBLE_RIGHT));
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        layout.add(header, contentDiv, button);

        HorizontalLayout container = new HorizontalLayout();
        container.setSpacing(false);
        container.getStyle().set("flex-wrap", "wrap");
        container.setSizeFull();
        container.getStyle().set("padding-left", "20%");
        container.getStyle().set("padding-right", "20%");

        String firstCardHeader = "Курс языка фактов";
        String firstCardContent = "Хотите научиться нестандартно орать? Тогда этот курс для вас! " +
                "По окончании обучения you'll be spitting fax. Как говорит наш преподаватель - " +
                "Да не умер я в конце курса!";

        String secondCardHeader = "Курс эльфийского языка";
        String secondCardContent = "Прежде чем записаться на этот курс, ответьте всего лишь на 3 вопроса "
                + "от нашего преподавателя:" +
                " 1) What country u from?" +
                " 2) They speak elvish there?" +
                " 3) Elvish, bloede dhoine, do u speak it?!";


        Component card1 = createCard(firstCardHeader, firstCardContent);
        Component card2 = createCard(secondCardHeader, secondCardContent);
//        Component card3 = createCard(cardHeader, cardContent);

        Image gosling = new Image("images/Gosling.jpg", "Тут должен был быть Райан...");
        gosling.setWidth("250px");
        gosling.setHeight("250px");

        Image samuel = new Image("images/Elvish.png", "Тут должен был быть Сэмюэль...");
        samuel.setWidth("250px");
        samuel.setHeight("250px");

        VerticalLayout subcontainer1 = new VerticalLayout();
        subcontainer1.add(card1, gosling);
        VerticalLayout subcontainer2 = new VerticalLayout();
        subcontainer2.add(card2, samuel);
        container.add(card1, gosling, card2, samuel);
        container.setAlignItems(Alignment.CENTER);

        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);


        CookieConsent cookieConsent = new CookieConsent();
        cookieConsent.setMessage(
                "Мы тут используем куки. Смирись.");
        cookieConsent.setDismissLabel("Пон");
        cookieConsent.setLearnMoreLabel("Рид мор");
        cookieConsent.setLearnMoreLink("https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab_channel=RickAstley");


        add(layout, container,
                getContactStats(), getCompaniesChart(), cookieConsent);

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


        layout.getElement().getStyle().set("flex-grow", "1");
        layout.getElement().getStyle().set("margin", "10px");
        layout.add(header, content);
        return layout;
    }
}