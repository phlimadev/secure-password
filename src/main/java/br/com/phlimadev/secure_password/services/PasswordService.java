package br.com.phlimadev.secure_password.services;

import br.com.phlimadev.secure_password.dtos.PasswordDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PasswordService {
    private static final Pattern UPPER_CASE = Pattern.compile("[A-Z]");
    private static final Pattern LOWER_CASE = Pattern.compile("[a-z]");
    private static final Pattern NUMERIC_DIGIT = Pattern.compile("[\\d]");
    private static final Pattern SPECIAL_CHARACTER = Pattern.compile("[@!#$%^&*\\-+=]");
    private static final List<String> invalidations = new ArrayList<>();

    public ResponseEntity validatePassword(PasswordDTO passwordDTO) {
        invalidations.clear();
        String password = passwordDTO.password();

        checkingCondition(password.length() >= 8, "The password does not have 8 characters.");
        checkingCondition(UPPER_CASE.matcher(password).find(), "The password does not have a capital letter.");
        checkingCondition(LOWER_CASE.matcher(password).find(), "The password does not have a lowercase letter.");
        checkingCondition(NUMERIC_DIGIT.matcher(password).find(), "The password does not have a numeric digit.");
        checkingCondition(SPECIAL_CHARACTER.matcher(password).find(), "The password does not have a special character.");

        if (invalidations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.badRequest().body(invalidations);
    }

    private void checkingCondition(boolean condition, String message) {
        if (!condition) {
            invalidations.add(message);
        }
    }
}
