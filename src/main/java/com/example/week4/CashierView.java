package com.example.week4;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@Route(value = "index2")
@RestController
public class CashierView extends VerticalLayout {
//    t_c = text change
//    t_c1000 = change 1000
//    t_c500 = change 500
//    t_c100 = change 100
//    t_c20 = change 20
//    t_c10 = change 10
//    t_c5 = change 5
//    t_c1 = change 1
    private TextField t_c, t_c1000, t_c500, t_c100, t_c20, t_c10, t_c5, t_c1;

    //btnChangeCalculation = คำนวณเงิน
    private Button btnChangeCalculation;

    public CashierView(){
        t_c = new TextField("เงินทอน");
        t_c.setPrefixComponent(new Span("$"));

        btnChangeCalculation = new Button("คำนวณเงินทอน");

        t_c1000 = new TextField();
        t_c1000.setPrefixComponent(new Span("$1000:"));
        t_c1000.setReadOnly(true);

        t_c500 = new TextField();
        t_c500.setPrefixComponent(new Span("$500:"));
        t_c500.setReadOnly(true);

        t_c100 = new TextField();
        t_c100.setPrefixComponent(new Span("$100:"));
        t_c100.setReadOnly(true);

        t_c20 = new TextField();
        t_c20.setPrefixComponent(new Span("$20:"));
        t_c20.setReadOnly(true);

        t_c10 = new TextField();
        t_c10.setPrefixComponent(new Span("$10:"));
        t_c10.setReadOnly(true);

        t_c5 = new TextField();
        t_c5.setPrefixComponent(new Span("$5:"));
        t_c5.setReadOnly(true);

        t_c1 = new TextField();
        t_c1.setPrefixComponent(new Span("$1:"));
        t_c1.setReadOnly(true);

        this.add(t_c,btnChangeCalculation,t_c1000,t_c500,t_c100,t_c20,t_c10,t_c5,t_c1);

        //   "/getChange/{money}"
        btnChangeCalculation.addClickListener(event -> {
            //"WebClient.create()" : สร้างอินสแตนซ์ของ WebClient เพื่อเตรียมสร้าง HTTP request
            Change out = WebClient.create()
                    .get()//กำหนดว่าจะใช้ HTTP method GET ในการส่ง request.
                    .uri("http://localhost:8080/" + "getChange/" + t_c.getValue())
                    .retrieve()//ส่ง request และรอรับ response กลับมา.
                    .bodyToMono(Change.class)
                    .block() ;
            t_c1000.setValue(String.valueOf(out.getB1000()));
            t_c500.setValue(String.valueOf(out.getB500()));
            t_c100.setValue(String.valueOf(out.getB100()));
            t_c20.setValue(String.valueOf(out.getB20()));
            t_c10.setValue(String.valueOf(out.getB10()));
            t_c5.setValue(String.valueOf(out.getB5()));
            t_c1.setValue(String.valueOf(out.getB1()));
//            t_c.setValue(out);
        });
    }
}
