package luccas.manager.models;

import jakarta.persistence.*;
import lombok.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuariosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String firstName;
    private String lastName;
    private String fullName;
    private String CPF;

    private UUID privateKey;
    private String hash;
    private String codex;
    private String email;
    private String senha;
    private String celular;

    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String endereco;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void antesGravar() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void antesAtualizar() {
        this.updatedAt = LocalDateTime.now();
    }

    private void gerarCodex() {
        try {

            String input = this.hash + this.privateKey;
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] keyBytes = sha.digest(input.getBytes("UTF-8"));
            this.setCodex(Base64.getEncoder().encodeToString(keyBytes));

        } catch (Exception e) {

            throw new RuntimeException("Erro ao gerar codex", e);

        }
    }

    public void criptografarSenha() {
        gerarCodex();
        try {
            SecretKeySpec secretKey = gerarChaveCodex();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            byte[] ivBytes = new byte[16];
            new SecureRandom().nextBytes(ivBytes);
            IvParameterSpec iv = new IvParameterSpec(ivBytes);

            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] encrypted = cipher.doFinal(this.senha.getBytes(StandardCharsets.UTF_8));

            ByteBuffer bb = ByteBuffer.allocate(16 + encrypted.length);
            bb.put(ivBytes);
            bb.put(encrypted);

            this.senha = Base64.getEncoder().encodeToString(bb.array());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criptografar senha", e);
        }
    }

    public String descriptografarSenha() {
        try {
            SecretKeySpec secretKey = gerarChaveCodex();
            byte[] ivAndEncrypted = Base64.getDecoder().decode(this.senha);
            ByteBuffer bb = ByteBuffer.wrap(ivAndEncrypted);

            byte[] ivBytes = new byte[16];
            bb.get(ivBytes);
            byte[] encrypted = new byte[bb.remaining()];
            bb.get(encrypted);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));
            byte[] decrypted = cipher.doFinal(encrypted);

            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao descriptografar senha", e);
        }
    }

    private SecretKeySpec gerarChaveCodex() throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(this.codex);
        return new SecretKeySpec(keyBytes, 0, 16, "AES"); // AES-128
    }
}
