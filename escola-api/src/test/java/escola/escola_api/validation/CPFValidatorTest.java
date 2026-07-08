package escola.escola_api.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CPFValidatorTest {

    private final CPFValidator validator = new CPFValidator();

    @Test
    void cpfValidoDevePassar() {
        assertTrue(validator.isValid("52998224725", null));
    }

    @Test
    void cpfComFormatacaoValidoDevePassar() {
        assertTrue(validator.isValid("529.982.247-25", null));
    }

    @Test
    void cpfComTodosDigitosIguaisDeveFalhar() {
        assertFalse(validator.isValid("11111111111", null));
    }

    @Test
    void cpfComTamanhoErradoDeveFalhar() {
        assertFalse(validator.isValid("123456789", null));
    }

    @Test
    void cpfNuloDeveFalhar() {
        assertFalse(validator.isValid(null, null));
    }

    @Test
    void cpfComDigitoVerificadorErradoDeveFalhar() {
        assertFalse(validator.isValid("52998224700", null));
    }
}