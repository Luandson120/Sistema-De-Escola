package escola.escola_api.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFValidator implements ConstraintValidator<CPFValido, String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null) {
            return false;
        }

        String cpfLimpo = cpf.replaceAll("[^0-9]", "");

        if (cpfLimpo.length() != 11) {
            return false;
        }

        if (cpfLimpo.chars().distinct().count() == 1) {
            return false;
        }

        return validarDigitos(cpfLimpo);
    }

    private boolean validarDigitos(String cpf) {
        int[] numeros = cpf.chars().map(c -> c - '0').toArray();

        int primeiroDigito = calcularDigito(numeros, 9, 10);
        if (primeiroDigito != numeros[9]) {
            return false;
        }

        int segundoDigito = calcularDigito(numeros, 10, 11);
        return segundoDigito == numeros[10];
    }

    private int calcularDigito(int[] numeros, int quantidade, int pesoInicial) {
        int soma = 0;
        for (int i = 0; i < quantidade; i++) {
            soma += numeros[i] * (pesoInicial - i);
        }

        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
}