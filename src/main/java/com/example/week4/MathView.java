package com.example.week4;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Route(value = "index1")
@RestController
public class MathView extends VerticalLayout {
    private TextField n1, n2, n3;
    private Button btnPlus, btnMinus, btnMulti, btnDivide, btnMod, btnMax;

    public MathView(){
        n1 = new TextField("Number 1");
        n2 = new TextField("Number 2");
        n3 = new TextField("Answer");
        n3.setReadOnly(true); // กำหนดให้ไม่สามารถแก้ไขข้อความได้

        btnPlus = new Button("+");
        btnMinus = new Button("-");
        btnMulti = new Button("x");
        btnDivide = new Button("/");
        btnMod = new Button("Mod");
        btnMax = new Button("Max");

        // สร้างคอมโพเนนต์ Text และเพิ่มลงใน layout
        // chatGPT แนะนำ span
        Span label = new Span("Operator");

        HorizontalLayout hL = new HorizontalLayout();//ซ้าย-ขวา
        VerticalLayout vL = new VerticalLayout();//บน-ล่าง
        FormLayout F1 = new FormLayout();
        FormLayout F2 = new FormLayout();
        FormLayout F3 = new FormLayout();


        F1.add(n1);
        F2.add(n2);
        hL.add(btnPlus, btnMinus, btnMulti, btnDivide, btnMod, btnMax);
        F3.add(n3);
        this.add(F1,F2, label, hL, F3);

        //   "/plus/{n1}/{n2}"
        btnPlus.addClickListener(event -> {
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/" + "plus/" + n1.getValue() + "/" + n2.getValue())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block() ;
            n3.setValue(out);
        });

        //   "/minus/{n1}/{n2}"
        btnMinus.addClickListener(event -> {
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/" + "minus/" + n1.getValue() + "/" + n2.getValue())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block() ;
            n3.setValue(out);
        });

        //   "/multi/{n1}/{n2}"
        btnMulti.addClickListener(event -> {
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/" + "multi/" + n1.getValue() + "/" + n2.getValue())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block() ;
            n3.setValue(out);
        });

        //   "/divide/{n1}/{n2}"
        btnDivide.addClickListener(event -> {
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/" + "divide/" + n1.getValue() + "/" + n2.getValue())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block() ;
            n3.setValue(out);
        });

        //   "/mod/{n1}/{n2}"
        btnMod.addClickListener(event -> {
            String out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/" + "mod/" + n1.getValue() + "/" + n2.getValue())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block() ;
            n3.setValue(out);
        });

        //   "/max"
        btnMax.addClickListener(event ->{
            //การส่งค่าหลายค่าผ่าน Form ด้วยวิธี MultiValueMap ในรูปแบบ String และ String
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            //(Keu , Value)
            formData.add("n1", n1.getValue());
            formData.add("n2", n2.getValue());

            String out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/max")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            n3.setValue(out);
        });
//        //   "/max/{n1}/{n2}"
//        btnMax.addClickListener(event -> {
//            String out = WebClient.create()
//                    .post()
//                    .uri("http://localhost:8080/" + "max/" + n1.getValue() + "/" + n2.getValue())
//                    .retrieve()
//                    .bodyToMono(String.class)
//                    .block() ;
//            n3.setValue(out);
//        });

    }
}
