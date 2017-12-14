package edu.karazin.shop;

import edu.karazin.shop.model.Product;
import edu.karazin.shop.model.User;
import edu.karazin.shop.model.enums.Role;
import edu.karazin.shop.repository.ProductRepository;
import edu.karazin.shop.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.sql.SQLException;

@SpringBootApplication
public class ShopApplication extends SpringBootServletInitializer {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public ShopApplication(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        userRepository.save(new User("admin", "admin",Role.ROLE_ADMIN));
        userRepository.save(new User("ValeraUser", "userpass",Role.ROLE_USER));
        userRepository.save(new User("ValeraFedUser", "userpass",Role.ROLE_USER));
        productRepository.save(new Product(null, "Socks", "some desc", 30, 20));
        productRepository.save(new Product(null, "Meat", "some desc 2", 50, 20));
    }


    public static void main(String[] args) throws SQLException {
		SpringApplication.run(ShopApplication.class, args);
        //Server.createTcpServer().start()         ;
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ShopApplication.class);
    }

}
