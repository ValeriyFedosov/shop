package edu.karazin.shop.web;

import edu.karazin.shop.model.enums.Role;
import edu.karazin.shop.model.User;

public class UserForm extends LoginForm {
    private Role role;

    public UserForm() {
    }

    public UserForm(Role role) {
        this.role = role;
    }

    public UserForm(String login, String password, Role role) {
        super(login, password);
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User convertToUser() {
        return new User(getLogin(), getPassword(), getRole());
    }
}
