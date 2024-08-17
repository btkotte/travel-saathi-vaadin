package com.example.application.views.home;

import com.example.application.data.SamplePerson;
import com.example.application.services.SamplePersonService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@PageTitle("Home")
@Route(value = "home", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class HomeView extends Composite<VerticalLayout> {

    public HomeView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        HorizontalLayout layoutRow = new HorizontalLayout();
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        DatePicker datePicker = new DatePicker();
        DatePicker datePicker2 = new DatePicker();
        Button buttonPrimary = new Button();
        Grid stripedGrid = new Grid(SamplePerson.class);
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setJustifyContentMode(JustifyContentMode.START);
        layoutColumn2.setAlignItems(Alignment.START);
        layoutRow.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("100px");
        textField.setLabel("Origin Airport");
        textField.setWidth("min-content");
        textField2.setLabel("Destination Airport");
        layoutRow.setAlignSelf(FlexComponent.Alignment.START, textField2);
        textField2.setWidth("min-content");
        datePicker.setLabel("Travel Between");
        datePicker.setWidth("min-content");
        datePicker2.setLabel("Travel To");
        datePicker2.setWidth("min-content");
        buttonPrimary.setText("Search");
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, buttonPrimary);
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        stripedGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        stripedGrid.setWidth("100%");
        stripedGrid.getStyle().set("flex-grow", "0");
        setGridSampleData(stripedGrid);
        getContent().add(layoutColumn2);
        layoutColumn2.add(layoutRow);
        layoutRow.add(textField);
        layoutRow.add(textField2);
        layoutRow.add(datePicker);
        layoutRow.add(datePicker2);
        layoutRow.add(buttonPrimary);
        layoutColumn2.add(stripedGrid);
    }

    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> samplePersonService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
    }

    @Autowired()
    private SamplePersonService samplePersonService;
}
