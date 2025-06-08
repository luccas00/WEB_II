package luccas.manager.domain.usecase;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import luccas.manager.domain.UsuariosDomain;

@AllArgsConstructor
@RequiredArgsConstructor
public class CreateUsuarioUseCase {

    UsuariosDomain domain;

    public void validar() {
        validarNome();
        validarEmail();
        validarSenha();
        validarCPF();
    }

    private void validarNome() {
        if (this.domain.getFirstName() == null || this.domain.getLastName() == null) {
            throw new RuntimeException("FirstName and LastName must not be null");
        }
    }

    private void validarEmail() {
        if (this.domain.getEmail() == null) {
            throw new RuntimeException("Email must not be null");
        }
    }

    private void validarSenha() {
        if (this.domain.getSenha() == null) {
            throw new RuntimeException("Senha must not be null");
        }
    }

    private void validarCPF() {
        if (this.domain.getCPF() == null) {
            throw new RuntimeException("CPF must not be null");
        }
        if (!validarNumeroCPF())
            throw new RuntimeException("CPF invalido");
    }

    private boolean validarNumeroCPF() {
        String cpf = this.domain.getCPF();

        if (cpf == null) return false;

        cpf = cpf.replaceAll("[^\\d]", ""); // Remove pontos e traços

        if (cpf.length() != 11 || cpf.chars().distinct().count() == 1) return false;

        try {
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int resto = soma % 11;
            int digito1 = (resto < 2) ? 0 : 11 - resto;

            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            resto = soma % 11;
            int digito2 = (resto < 2) ? 0 : 11 - resto;

            return cpf.endsWith("" + digito1 + digito2);
        } catch (Exception e) {
            return false;
        }
    }



}
