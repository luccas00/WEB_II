package luccas.manager.controllers;

import lombok.AllArgsConstructor;
import luccas.manager.dto.usuarios.UsuariosRecordDTO;
import luccas.manager.models.UsuariosModel;
import luccas.manager.service.UsuariosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class HomeController {

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Status of Service is: Online");
    }

    @GetMapping
    public ResponseEntity<String> getIndex() {
        return ResponseEntity.ok("Hello There - Service is running");
    }

    @PostMapping("/decript/codex")
    public ResponseEntity<String> decriptCodex(@RequestBody Map<String, String> payload) {

        String senha = payload.get("senha");
        String codex = payload.get("codex");

        return ResponseEntity.ok(descriptografarSenha(senha, codex));
    }

    public String descriptografarSenha(String senha, String codex) {

        try {
            SecretKeySpec secretKey = gerarChaveCodex(codex);
            byte[] ivAndEncrypted = Base64.getDecoder().decode(senha);
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

    private SecretKeySpec gerarChaveCodex(String codex) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(codex);
        return new SecretKeySpec(keyBytes, 0, 16, "AES"); // AES-128
    }

}
