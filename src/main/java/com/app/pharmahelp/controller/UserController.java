package com.app.pharmahelp.controller;

import com.app.pharmahelp.entities.*;
import com.app.pharmahelp.security.CurrentUser;
import com.app.pharmahelp.security.UserPrincipal;
import com.app.pharmahelp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService = new UserService();


    @Autowired
    private PharmaciesService pharmaciesService = new PharmaciesService();

    @Autowired
    private DrugService drugService = new DrugService();

    @Autowired
    private OrderService orderService = new OrderService();

    @Autowired
    private ReviewService reviewService = new ReviewService();

    @Autowired
    private PharmacyStockService pharmacyStockService = new PharmacyStockService();

    @Autowired
    private RequestForAdditionalService requestForAdditionalService = new RequestForAdditionalService();

    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping("/generate")
    public String generate() throws ParseException {

        User user1 = new User();
        user1.setEmail("patient@email.com");
        user1.setPassword(passwordEncoder.encode("parola"));
        user1.setFullName("Patient");
        user1.setRole("patient");
        userService.saveUser(user1);


         User user2 = new User();
         user2.setEmail("admin@email.com");
         user2.setPassword(passwordEncoder.encode("parola"));
         user2.setFullName("Admin");
         user2.setRole("admin");
         userService.saveUser(user2);


          User user3 = new User();
	      user3.setEmail("pharmacist@email.com");
		  user3.setPassword(passwordEncoder.encode("parola"));
		  user3.setFullName("George");
		  user3.setRole("pharmacist");
          userService.saveUser(user3);

          Pharmacist ph = new Pharmacist();
          ph.setUser(user3);

          List<Pharmacist> pharmacists = new ArrayList<>();
          pharmacists.add(ph);

		  Pharmacy pharma = new Pharmacy();
		  pharma.setName("Catena");
		  pharma.setLocation("Cluj-Napoca");
		  pharma.setPharmacistsList(pharmacists);
		  pharmaciesService.saveOPharmacy(pharma);

         Drug drug1 = new Drug();
         drug1.setDetails("drug1");
         drug1.setName("drugName1");
         drug1.setStatus("approved");
         drug1.setProspectus("prospect");
         drug1.setActiveSubstance("substance1");

         Drug drug2 = new Drug();
         drug2.setDetails("drug2");
         drug2.setName("drugName2");
         drug2.setStatus("approved");
         drug2.setProspectus("prospect2");
         drug2.setActiveSubstance("substance2");

         drugService.saveDrug(drug1);
         drugService.saveDrug(drug2);

        Order order = new Order();
        order.setUser(user1);

        List<DrugPerOrder> drugPerOrderList = new ArrayList<DrugPerOrder>();
        DrugPerOrder drugPerOrder1 = new DrugPerOrder();
        DrugPerOrder drugPerOrder2 = new DrugPerOrder();
        drugPerOrder1.setDrug(drug1);
        drugPerOrder2.setDrug(drug2);
        drugPerOrder1.setOrder(order);
        drugPerOrder2.setOrder(order);
        drugPerOrderList.add(drugPerOrder1);
        drugPerOrderList.add(drugPerOrder2);

        order.setDrugPerOrders(drugPerOrderList);
        order.setStatus("pending");
        order.setPharmacy(pharma);
        orderService.saveOrder(order);


        Reviews review1 = new Reviews();
        review1.setComment("comentariu1");
        review1.setCreatedDate(new Date());
        review1.setDrug(drug1);
        review1.setRating(4);
        review1.setStatus("approved");
        review1.setUser(user2);
        reviewService.saveReviews(review1);

        PharmacyStock ps1 = new PharmacyStock();
        ps1.setDrug(drug1);
        ps1.setPrice((long) 9.99);
        ps1.setQuantity(5);
        ps1.setPharmacy(pharma);
        pharmacyStockService.savePharmacyStock(ps1);

        RequestForAdditional requestForAdditional1 = new RequestForAdditional();
        requestForAdditional1.setLocation("test test");
        requestForAdditional1.setStatus("pending");
        requestForAdditional1.setDrug(drug1);
        requestForAdditional1.setUser(user1);

        requestForAdditionalService.saveRequest(requestForAdditional1);

        RequestForAdditional requestForAdditional2 = new RequestForAdditional();
        requestForAdditional2.setLocation("test");
        requestForAdditional2.setStatus("approved");
        requestForAdditional2.setDrug(drug2);
        requestForAdditional2.setUser(user1);

        requestForAdditionalService.saveRequest(requestForAdditional2);

        return "succes";
    }

    @GetMapping("/user/me")
    public User getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        User user = new User();
        user.setId_user(currentUser.getId());
        user.setEmail(currentUser.getEmail());
        user.setFullName(currentUser.getName());
        user.setRole(currentUser.getRole());
        user.setPassword(currentUser.getPassword());
        return user;
    }
}
