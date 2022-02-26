package bootcamp.skooldio.assignment;

import bootcamp.skooldio.assignment.model.ProductEntity;
import bootcamp.skooldio.assignment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AssignmentApplication {

	@Autowired
	private ProductRepository productRepository;

	@PostConstruct
	public void add10NikeToProducts() {
		ProductEntity nike1 = new ProductEntity()
				.setProductName("Nike 1")
				.setNumberInStock(5)
				.setProductSummary("One in the Nike Number Series")
				.setProductPrice(999.00);
		productRepository.save(nike1);
		ProductEntity nike2 = new ProductEntity()
				.setProductName("Nike 2")
				.setNumberInStock(5)
				.setProductSummary("One in the Nike Number Series")
				.setProductPrice(999.00);
		productRepository.save(nike2);
		ProductEntity nike3 = new ProductEntity()
				.setProductName("Nike 3")
				.setNumberInStock(5)
				.setProductSummary("One in the Nike Number Series")
				.setProductPrice(999.00);
		productRepository.save(nike3);
		ProductEntity nike4 = new ProductEntity()
				.setProductName("Nike 4")
				.setNumberInStock(5)
				.setProductSummary("One in the Nike Number Series")
				.setProductPrice(999.00);
		productRepository.save(nike4);
		ProductEntity nike5 = new ProductEntity()
				.setProductName("Nike 5")
				.setNumberInStock(5)
				.setProductSummary("One in the Nike Number Series")
				.setProductPrice(999.00);
		productRepository.save(nike5);
		ProductEntity nike6 = new ProductEntity()
				.setProductName("Nike 6")
				.setNumberInStock(5)
				.setProductSummary("One in the Nike Number Series")
				.setProductPrice(999.00);
		productRepository.save(nike6);
		ProductEntity nike7 = new ProductEntity()
				.setProductName("Nike 7")
				.setNumberInStock(5)
				.setProductSummary("One in the Nike Number Series")
				.setProductPrice(999.00);
		productRepository.save(nike7);
		ProductEntity nike8 = new ProductEntity()
				.setProductName("Nike 8")
				.setNumberInStock(5)
				.setProductSummary("One in the Nike Number Series")
				.setProductPrice(999.00);
		productRepository.save(nike8);
		ProductEntity nike9 = new ProductEntity()
				.setProductName("Nike 9")
				.setNumberInStock(5)
				.setProductSummary("One in the Nike Number Series")
				.setProductPrice(999.00);
		productRepository.save(nike9);
		ProductEntity nike10 = new ProductEntity()
				.setProductName("Nike 10")
				.setNumberInStock(5)
				.setProductSummary("One in the Nike Number Series")
				.setProductPrice(999.00);
		productRepository.save(nike10);
	}

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

}
