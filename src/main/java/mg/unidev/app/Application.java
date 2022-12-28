package mg.unidev.app;


import mg.unidev.app.entities.*;
import mg.unidev.app.repository.AccountRepository;
import mg.unidev.app.repository.CustomerRepository;
import mg.unidev.app.repository.OperationRepository;
import mg.unidev.app.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository, AccountRepository accountRepository, OperationRepository operationRepository,
							BankService bankService) {
		return args -> {
			Customer customer1 = customerRepository.save(Customer.builder().name("Solo Soaniaina").email("solo@gmail.com").build());
			Customer customer2 = customerRepository.save(Customer.builder().name("Miora Milantonirina").email("milanto@gmail.com").build());
			Customer customer3 = customerRepository.save(Customer.builder().name("Pakasoa Nomen'ny Tompo").email("pakasoa.nomena@gmail.com").build());

			Account account1 = accountRepository.save(SavingsAccount.builder().code("account-one").customer(customer2).solde(100+Math.random()*900).taux(1+Math.random()*10).build());
			Account account2 = accountRepository.save(CurrentAccount.builder().code("account-two").customer(customer1).solde(100+Math.random()*900).decouvert(100+Math.random()*900).build());

			operationRepository.save(Versement.builder().solde(100+Math.random()*900).account(account1).build());
			operationRepository.save(Versement.builder().solde(100+Math.random()*900).account(account2).build());
			operationRepository.save(Retrait.builder().solde(100+Math.random()*900).account(account1).build());
			operationRepository.save(Retrait.builder().solde(100+Math.random()*900).account(account2).build());

			bankService.verser(account1.getCode(), 111111);
			bankService.virement(account1.getCode(), account2.getCode(), 5555);

			bankService.listOperation(account1.getCode()).forEach(System.out::println);
		};
	}
}
