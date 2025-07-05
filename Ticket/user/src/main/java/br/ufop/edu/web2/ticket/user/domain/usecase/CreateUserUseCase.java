package br.ufop.edu.web2.ticket.user.domain.usecase;

import br.ufop.edu.web2.ticket.user.domain.UserDomain;
import br.ufop.edu.web2.ticket.user.util.Password;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class CreateUserUseCase {

    UserDomain userDomain;

    public void validate() throws Exception {

        // Regras de negócio - conforme com o caso de uso
        validateName();
        encriptPassword();
        // Demais validações


    }

    private void validateName() {

        if (this.userDomain.getName() == null) {
            throw new RuntimeException("Name is null");
        }


    }

    private void encriptPassword() throws Exception {

        if (this.userDomain.getPassword() == null) {
            throw new RuntimeException("Password is null");
        }

        if (this.userDomain.getKey() == null) {
            throw new RuntimeException("Key is null");
        }

        this.userDomain.setPassword(Password.encrypt(this.userDomain.getKey(), this.userDomain.getPassword()));

    }

}
