package mg.unidev.app.services;

import mg.unidev.app.entities.*;
import mg.unidev.app.repository.AccountRepository;
import mg.unidev.app.repository.OperationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class BankServiceImpl implements BankService {

    private final AccountRepository accountRepository;
    private final OperationRepository operationRepository;

    public BankServiceImpl(AccountRepository accountRepository, OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    public Account getAccount(String codeAcc) {
        return accountRepository.findById(codeAcc).orElseThrow(() -> new RuntimeException("ACCOUNT NOT FOUND IN THE DATABASE !!"));
    }

    @Override
    public void verser(String codeAcc, double amount) {
        Account account = getAccount(codeAcc);
        Versement versement = Versement.builder().account(account).solde(amount).build();
        operationRepository.save(versement);
        account.setSolde(account.getSolde()+amount);
        accountRepository.save(account);
    }

    @Override
    public void retirer(String codeAcc, double amount) {
        Account account = getAccount(codeAcc);

        double x = 0;
        if (account instanceof CurrentAccount) {
            x = ((CurrentAccount) account).getDecouvert();
        }
        if (account.getSolde() + x < amount) {
            throw new RuntimeException("Solde Insuffisant !!");
        }

        Retrait retrait = Retrait.builder().account(account).solde(amount).build();
        operationRepository.save(retrait);
        account.setSolde(account.getSolde()-amount);
        accountRepository.save(account);
    }

    @Override
    public void virement(String codeAcc1, String codeAcc2, double amount) {
        if (codeAcc1.equals(codeAcc2)) throw new RuntimeException("On peut pas effectuer un virement sur le même compte !!");
        retirer(codeAcc1, amount);
        verser(codeAcc2, amount);
    }

    @Override
    public Page<Operation> listOperation(String codeAcc, int size, int page) {
//        return operationRepository.getAllByAccount_CodeOrderByDateOperationDesc(codeAcc, PageRequest.of(page, size, Sort.Direction.DESC, "dateOperation"));
        return operationRepository.getOperationsByAccount_CodeOrderByDateOperationDesc(codeAcc, PageRequest.of(page, size));
    }

    @Override
    public List<Operation> listOperation(String codeAcc) {
        return operationRepository.findAllByAccount_CodeOrderByDateOperationDesc(codeAcc);
    }

}
