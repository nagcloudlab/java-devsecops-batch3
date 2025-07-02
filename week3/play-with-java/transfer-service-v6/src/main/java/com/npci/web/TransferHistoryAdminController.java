package com.npci.web;

import com.npci.model.TransferHistory_By_Hour;
import com.npci.repository.TransferHistoryByHourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/admin/history")
public class TransferHistoryAdminController {

    private final TransferHistoryByHourRepository historyRepository;

    private static final DateTimeFormatter HOUR_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd-HH").withZone(ZoneId.systemDefault());

    @Autowired
    public TransferHistoryAdminController(TransferHistoryByHourRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @GetMapping
    public String showHourlyHistory(
            @RequestParam(value = "hourBucket", required = false)
            String hourBucket,
            Model model
    ) {

        Authentication authentication = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        System.out.println("✅ Current User: " + authentication.getName());
        authentication.getAuthorities().forEach(authority ->
                System.out.println("➡️ Authority: " + authority.getAuthority()));

        if (hourBucket != null) {
            try {
                List<TransferHistory_By_Hour> transactions = historyRepository.findByKeyHourBucket(hourBucket);
                System.out.println("✅ Fetched " + transactions.size() + " records");

                // Never log full objects to avoid StackOverflowError
                transactions.forEach(tx -> {
                    System.out.println("TX ID: " + tx.getKey().getTransactionId()
                            + ", Amount: " + tx.getAmount());
                });

                model.addAttribute("transactions", transactions);
            } catch (Exception e) {
                e.printStackTrace(); // ✅ Show root cause
            }
        }

        return "admin-history";
    }


}
