package com.example.week4;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route(value = "index4")
public class test extends FormLayout {
    public test() {
        TextField firstNameField = new TextField();
        firstNameField.setPlaceholder("First name");
        TextField lastNameField = new TextField();
        lastNameField.setPlaceholder("Last name");
        Button btn = new Button("Send");
        this.add(firstNameField, lastNameField,btn);
//        this.setResponsiveSteps(
//// Use one column by default
//                new ResponsiveStep("1px", 1),
//// Use two columns, if the layout's width exceeds 450px
//                new ResponsiveStep("600px", 2),
//// Use three columns, if the layout's width exceeds 700px
//                new ResponsiveStep("700px", 3));
    }
}
