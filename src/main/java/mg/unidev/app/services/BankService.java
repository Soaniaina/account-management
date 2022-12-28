package mg.unidev.app.services;

import mg.unidev.app.entities.Account;
import mg.unidev.app.entities.Operation;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BankService {

    // Récuperer un compte en connaissant son code
    public Account getAccount(String codeAcc);
    // Depôt
    public void verser(String codeAcc, double amount);
    // Retrait
    public void retirer(String codeAcc, double amount);
    // Virement entre 2 compte
    public void virement(String codeAcc1, String codeAcc2, double amount);
    // Liste des operation d'un compte page par page, size nbre d'enregistrement d'une page et page le n° du page
    public Page<Operation> listOperation(String codeAcc, int size, int page);

    public List<Operation> listOperation(String codeAcc);

}
