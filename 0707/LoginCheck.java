public class LoginCheck {
    public static void main(String[] args) {
        String username = "admin";
        String password = "1234";

        String inputUsername = "admin";
        String inputPassword = "1234";

        boolean isLoginSuccess = inputUsername.equals(username) && inputPassword.equals(password);

        System.out.println("嘗試登入的帳號：" + inputUsername);
        System.out.println("嘗試登入的密碼：" + inputPassword);
        System.out.println("登入成功： " + isLoginSuccess);
    }
}
