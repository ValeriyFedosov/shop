package edu.karazin.shop.controller;

import edu.karazin.shop.service.HistoryService;
import edu.karazin.shop.service.UserService;
import edu.karazin.shop.util.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("history")
public class HistoryController {

    private final HistoryService historyService;
    private final UserService userService;
    private final ProductUtil productUtil;

    public HistoryController(@Autowired HistoryService historyService, @Autowired UserService userService,
                             @Autowired ProductUtil productUtil) {
        this.userService = userService;
        this.historyService = historyService;
        this.productUtil = productUtil;
    }

    @GetMapping
    public String getHistory(Model model) {
        model.addAttribute("history", productUtil.convertPurchaseItemsToHistoryDtos(historyService.getByUserId(
                userService.getCurrentAuthenticatedUser())));
        return "history-list";

    }

}
