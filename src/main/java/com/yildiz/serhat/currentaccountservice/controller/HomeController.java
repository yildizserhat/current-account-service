package com.yildiz.serhat.currentaccountservice.controller;

import com.yildiz.serhat.currentaccountservice.controller.request.AccountCreateRequestDTO;
import com.yildiz.serhat.currentaccountservice.domain.entity.Customer;
import com.yildiz.serhat.currentaccountservice.domain.model.CustomerDetails;
import com.yildiz.serhat.currentaccountservice.domain.model.TransactionDetails;
import com.yildiz.serhat.currentaccountservice.service.AccountService;
import com.yildiz.serhat.currentaccountservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/ui")
public class HomeController {

    private final CustomerService customerService;
    private final AccountService accountService;

    @GetMapping("/customers")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.findAllCustomers();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/customers/{id}")
    public String view(Model model, @PathVariable Long id) {
        CustomerDetails response = customerService.getCustomerDetails(id);

        List<TransactionDetails> list = response.getAccounts().stream().flatMap(y -> y.getTransactions().stream())
                .map(TransactionDetails::buildTransactionDetails)
                .toList();

        model.addAttribute("customerId", id);
        model.addAttribute("customerFirstName", response.getFirstName());
        model.addAttribute("customerLastName", response.getLastName());
        model.addAttribute("transactions", list);
        return "transactions";
    }

    @PostMapping("/accounts")
    public String createAccount(@ModelAttribute("requestDTO") AccountCreateRequestDTO requestDTO) {
        accountService.createAccount(requestDTO);
        return "redirect:/v1/ui/customers/" + requestDTO.customerId();
    }


    @GetMapping("/accounts/{id}")
    public String getAccount(Model model, @PathVariable Long id) {
        AccountCreateRequestDTO requestDTO = new AccountCreateRequestDTO(id, BigDecimal.ZERO);
        model.addAttribute("requestDTO", requestDTO);
        return "accounts";
    }
}
