package edu.karazin.shop.service.impl;

import edu.karazin.shop.model.PurchaseItem;
import edu.karazin.shop.model.User;
import edu.karazin.shop.repository.PurchaseItemRepository;
import edu.karazin.shop.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final PurchaseItemRepository purchaseItemRepository;

    public HistoryServiceImpl(@Autowired PurchaseItemRepository purchaseItemRepository) {
        this.purchaseItemRepository = purchaseItemRepository;
    }

    @Override
    public List<PurchaseItem> getByUserId(User user) {
        List<PurchaseItem> purchaseItems = new ArrayList<>();
        for (PurchaseItem purchaseItem : purchaseItemRepository.findAll()) {
            if(purchaseItem.getUid().getId().equals(user.getId())) {
                purchaseItems.add(purchaseItem);
            }
        }
        return purchaseItems;
    }
}
