package escola.escola_api.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtUtilTest {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Test
    void deveGerarTokenEExtrairUsername() {
        String token = jwtUtil.gerarToken("admin");
        assertNotNull(token);
        assertEquals("admin", jwtUtil.extrairUsername(token));
    }

    @Test
    void tokenDeveSerValidoParaUsernameCorreto() {
        String token = jwtUtil.gerarToken("admin");
        assertTrue(jwtUtil.tokenValido(token, "admin"));
    }

    @Test
    void tokenNaoDeveSerValidoParaUsernameDiferente() {
        String token = jwtUtil.gerarToken("admin");
        assertFalse(jwtUtil.tokenValido(token, "outro"));
    }
}