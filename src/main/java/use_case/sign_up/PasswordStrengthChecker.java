package use_case.sign_up;

public class PasswordStrengthChecker {
    public static void checkStrength(String username, String email, String password) throws IllegalArgumentException{
        checkWithWeakPasswords(password);
        checkWithUsername(username, password);
        checkWithEmail(email, password);
        checkCharacters(password);
    }

    private static void checkWithWeakPasswords (String password) throws IllegalArgumentException {
        if (password.contains("12345") || password.contains("54321") || password.contains("11111")){
            throw new IllegalArgumentException("Your password contains weak sequences of characters.");
        }
        if (password.toLowerCase().contains("password") || password.toLowerCase().contains("drowssap") || password.toLowerCase().contains("admin")){
            throw new IllegalArgumentException("Your password contains weak sequences of characters.");
        }
        if (password.toLowerCase().contains("qwerty") || password.toLowerCase().contains("ytrewq")){
            throw new IllegalArgumentException("Your password contains weak sequences of characters.");
        }
    }

    private static void checkWithUsername(String username, String password) throws IllegalArgumentException{
        if (password.toLowerCase().contains(username.toLowerCase())){
            throw new IllegalArgumentException("Your password contains your username.");
        }
    }

    private static void checkWithEmail(String email, String password) throws IllegalArgumentException{
        String emailSection = email.toLowerCase().substring(email.toLowerCase().indexOf('@'));
        for (int i = 0; i < emailSection.length(); i++){
            if (emailSection.length() >= 5){
                String curser = ""
                        + emailSection.charAt(i)
                        + emailSection.charAt(i+1)
                        + emailSection.charAt(i+2)
                        + emailSection.charAt(i+3)
                        + emailSection.charAt(i+4);

                if (password.toLowerCase().contains(curser)){
                    throw new IllegalArgumentException("Your password contains your username.");
                }
            }
        }
        if (password.toLowerCase().contains(emailSection)){
            throw new IllegalArgumentException("Your password contains your email");
        }
    }

    private static void checkCharacters(String password){
        password = password.toLowerCase();
        for (int i = 0; i < password.length(); i++){
            char c = password.charAt(i);
            if (!Character.isDigit(c)){
                if (!Character.isLetter(c)){
                    if (!"!@#$%^&*(){}[]||?/".contains("" + c)){
                        throw new IllegalArgumentException("Your password is weak.");
                    }
                }
            }
        }
    }
}
