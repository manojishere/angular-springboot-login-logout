package com.lift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lift.config.repository.UserRepository;
import com.lift.config.service.AddressService;
import com.lift.config.service.UserService;
import com.lift.config.service.UserServiceImpl;
import com.lift.config.vo.Address;
import com.lift.config.vo.User;

@SpringBootApplication
public class LiftRankingApplication {

	private static final Logger logger = LoggerFactory.getLogger(LiftRankingApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(LiftRankingApplication.class, args);
	}
	
	
	@Bean
	  public CommandLineRunner demo(UserService service, AddressService addressService) {
	    return (args) -> {
	      // save a few customers
	      User user = new User();
	      user.setFirstName("Neetu");
	      user.setLastName("Singh");
	      user.setUserName("tellneetu");
	      user.setPassword("neetu12345");
	      user.setEmail("tellme@gmail.com");
	      user.setPhoneNumber(1234567890);
	      
	      Address address = new Address();
	      address.setAddress1("1234 Main Land");
	      address.setCity("Dallas");
	      address.setPostalCode(12345);
	      address.setState("TX");
	      
	      user.getAddress().add( address );
	      service.save(user);
	      
	      
	      
	      User user2 = new User();
	      user2.setFirstName("Manoj");
	      user2.setLastName("Singh");
	      user2.setUserName("manojishere");
	      user2.setPassword("msingh1");
	      user2.setEmail("tellmanoj@gmail.com");
	      user2.setPhoneNumber(9234567890L);
	      
	      
	      Address address2 = new Address();
	      address2.setAddress1("1234 Lake Rd");
	      address2.setCity("Sanvalley");
	      address2.setPostalCode(45678);
	      address2.setState("CA");
	      

	      user2.getAddress().add( address2 );
	      
	      service.save(user2);
	      
	      
	      User user3 = new User();
	      user3.setFirstName("Sanskriti");
	      user3.setLastName("Singh");
	      user3.setUserName("tellsanskriti");
	      user3.setPassword("sanskriti1234");
	      user3.setEmail("telltosans@gmail.com");
	      user3.setPhoneNumber(4567891111L);

	      User user3Created = service.save(user3);
	      
	      Address address3 = new Address();
	      address3.setAddress1("4567 Cedar ln");
	      address3.setCity("Alpharetta");
	      address3.setPostalCode(40056);
	      address3.setState("GA");
	      address3.setUser( user3 );
	      
	      addressService.save( address3 );

	      logger.info("Customers found with userName:");
	      logger.info("-------------------------------");
	      User customer = service.findByUserName("tellneetu");
	      if( customer != null ) {
		      logger.info("customer info : "+ customer);
		      logger.info("customer address : "+ customer.getAddress());
	      }
	      
	      
	      logger.info("Customers found with findByLastName:");
	      logger.info("-------------------------------");
	      
	      service.findByLastName("Singh").forEach( (value) -> {
	    	  logger.info("User info : " + value );
	      });
	      
	    };
	  }	

}
