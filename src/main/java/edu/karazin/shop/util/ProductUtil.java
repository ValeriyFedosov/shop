package edu.karazin.shop.util;

import edu.karazin.shop.dto.HistoryDto;
import edu.karazin.shop.dto.ProductDto;
import edu.karazin.shop.model.BasketItem;
import edu.karazin.shop.model.Product;
import edu.karazin.shop.model.PurchaseItem;
import edu.karazin.shop.service.CartStore;
import edu.karazin.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductUtil {

    private  ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    public ProductService getProductService() {
        return productService;
    }

    // conversion

    public BasketItem convertEntityToBasketItem(Product product) {
        BasketItem basketItem = new BasketItem();
        basketItem.setProduct(productService.getProduct(product.getId()));
        return basketItem;
    }

    public List<PurchaseItem> convertBasketItemsToPurchaseItems(List<BasketItem> BasketItems) {
        List<PurchaseItem> purchaseItems = new ArrayList<>();
        PurchaseItem purchaseItem;
        for (BasketItem inMemoryBasketItem : BasketItems) {
            purchaseItem = new PurchaseItem();
            purchaseItem.setCost(inMemoryBasketItem.getProduct().getCost());
            purchaseItem.setDescription(inMemoryBasketItem.getProduct().getDescription());
            purchaseItem.setImageName(inMemoryBasketItem.getProduct().getImageName());
            purchaseItem.setTitle(inMemoryBasketItem.getProduct().getTitle());
            purchaseItem.setPurchaseItemAmount(inMemoryBasketItem.getCountOfProducts());
            purchaseItem.setCountOfCost(inMemoryBasketItem.getCountOfCost());
            purchaseItems.add(purchaseItem);
        }
        return purchaseItems;
    }

    public List<HistoryDto> convertPurchaseItemsToHistoryDtos(List<PurchaseItem> purchaseItems) {
        List<HistoryDto> historyDtos = new ArrayList<>();
        HistoryDto historyDto;
        for (PurchaseItem purchaseItem : purchaseItems) {
            historyDto = new HistoryDto();
            historyDto.setCost(purchaseItem.getCost());
            historyDto.setDate(purchaseItem.getDate());
            historyDto.setDescription(purchaseItem.getDescription());
            historyDto.setImageName(purchaseItem.getImageName());
            historyDto.setTitle(purchaseItem.getTitle());
            historyDto.setPurchaseItemAmount(purchaseItem.getPurchaseItemAmount());
            historyDto.setCountOfCost(purchaseItem.getCountOfCost());
            historyDtos.add(historyDto);
        }
        return historyDtos;
    }

    public PurchaseItem convertProductToPurchaseItems(Product product) {
        PurchaseItem purchaseItem = new PurchaseItem();
        purchaseItem.setTitle(product.getTitle());
        purchaseItem.setDescription(product.getDescription());
        purchaseItem.setCost(product.getCost());
        purchaseItem.setImageName(product.getImageName());
        purchaseItem.setPurchaseItemAmount(1L);
        purchaseItem.setCountOfCost(product.getCost());
        return purchaseItem;
    }

    public List<ProductDto> convertProductsToProductDtos(List<Product> products) {
        List<ProductDto> productDtos = new ArrayList<>();
        ProductDto productDto;
        for (Product product : products) {
            productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setTitle(product.getTitle());
            productDto.setDescription(product.getDescription());
            productDto.setCost(product.getCost());
            productDto.setImageName(product.getImageName());
            productDto.setBalance(product.getBalance());
            productDtos.add(productDto);
        }
        return productDtos;
    }


    public void addTheSameProductToCart(BasketItem prod, List<BasketItem> inMemoryBasketItems) {
        for (BasketItem inMemoryBasketItem : inMemoryBasketItems) {
            if (inMemoryBasketItem.equals(prod)) {
                inMemoryBasketItem.setCountOfCost(inMemoryBasketItem.getCountOfCost() + inMemoryBasketItem.getProduct().getCost());
                inMemoryBasketItem.setCountOfProducts(inMemoryBasketItem.getCountOfProducts() + 1);
            }
        }
    }




    public Double countDiscount(Double discountValue, Double productCost) {
        return productCost - (productCost / 100 * discountValue);
    }

    public static String imgPersist(MultipartFile img) throws IOException {
        if (img.isEmpty()) return null;
        String imgName = img.getOriginalFilename();
        File upl = new File("src/main/resources/static/".concat(imgName));
        upl.createNewFile();
        try (FileOutputStream fout = new FileOutputStream(upl)) {
            fout.write(img.getBytes());
        }
        return imgName;
    }

    // order

    public boolean checkForExistanceAndDecrement(Long prodId) {
        if (productService.getProduct(prodId).getBalance() != 0) {
            Product product = productService.getProduct(prodId);
            product.setBalance(product.getBalance() - 1);
            productService.updateProduct(product);
            return true;
        }
        return false;
    }

    public boolean checkForExistanceForCartAndDecrement(List<BasketItem> products, CartStore cartStore) {
        for (BasketItem inMemoryBasketItem : cartStore.getProducts()) {
            if (!(inMemoryBasketItem.getCountOfProducts() <= inMemoryBasketItem.getProduct().getBalance()))
                return false;
        }
        for (BasketItem inMemoryBasketItem : products) {
            inMemoryBasketItem.getProduct().setBalance(inMemoryBasketItem.getProduct().getBalance() - inMemoryBasketItem.getCountOfProducts());
            productService.updateProduct(inMemoryBasketItem.getProduct());
        }
        return true;
    }


    // validation

    public static boolean validate(Product product) {
        String title = product.getTitle();
        String description = product.getDescription();
        double cost = product.getCost();
        long balance = product.getBalance();
        if (org.h2.util.StringUtils.isNumber(title) || title.toCharArray().length > 10
                || title.isEmpty()) return false;
        if(description.toCharArray().length > 1000) return false;
        return true;
    }


    public void convertProductsToBasketItems(List<BasketItem> products) {
        for (BasketItem basketItem : products) {
            basketItem.setCost(basketItem.getProduct().getCost());
            basketItem.setBalance(basketItem.getProduct().getBalance());
            basketItem.setDescription(basketItem.getProduct().getDescription());
            basketItem.setImageName(basketItem.getProduct().getImageName());
            basketItem.setTitle(basketItem.getProduct().getTitle());
            basketItem.setProduct_id(basketItem.getProduct().getId());
        }
    }

}
