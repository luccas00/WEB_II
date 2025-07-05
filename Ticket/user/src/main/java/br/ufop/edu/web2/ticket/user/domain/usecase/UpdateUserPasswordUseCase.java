package br.ufop.edu.web2.ticket.user.domain.usecase;

import br.ufop.edu.web2.ticket.user.util.Password;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
public class UpdateUserPasswordUseCase {

    private UUID key;
    private String emailModel;
    private String emailPassed;
    private String oldPasswordModel;
    private String oldPasswordPassed;

    public void validate() {
        validateEmail();
        validateOldPassword();
    }

    private void validateEmail() {
        if (!emailModel.equals(emailPassed)) {
            throw new RuntimeException("Invalid email.");
        }
    }

    private void validateOldPassword() {

        String oldPass = "";

        try {
            oldPass = Password.encrypt(key, oldPasswordPassed);
        } catch (Exception e) {
            throw new RuntimeException("Decrypt failed.");
        }

        oldPasswordPassed = oldPass;

        if (!oldPasswordModel.equals(oldPasswordPassed)) {
            throw new RuntimeException("Invalid old password.");
        }
    }


}
