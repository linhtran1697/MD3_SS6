public class User {
    private String fullName;
    private String email;
    private String phone;
    private String password;

    public User() {
    }

    public User(
            String fullName,
            String email,
            String phone,
            String password
    ) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void displayInfo() {
        System.out.println("Họ và tên: " + fullName);
        System.out.println("Email: " + email);
        System.out.println("Điện thoại: " + phone);
        System.out.println("Mật khẩu: " + password);
    }
}
