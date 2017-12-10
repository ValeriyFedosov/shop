package edu.karazin.shop.service;

import edu.karazin.shop.model.PurchaseItem;
import edu.karazin.shop.model.User;

import java.util.List;

public interface HistoryService {

    List<PurchaseItem> getByUserId(User user);
}
