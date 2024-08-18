import * as ko from 'knockout';
import 'oj-c/input-text';
import 'oj-c/input-password';
import 'oj-c/form-layout';
import 'ojs/ojbutton';
import 'ojs/ojknockout';

class DashboardViewModel {
    // Observable properties for login form
    loginUsername = ko.observable('');
    loginPassword = ko.observable('');

    // Observable properties for registration form
    regFirstName = ko.observable('');
    regLastName = ko.observable('');
    regDateOfBirth = ko.observable('');
    regAddress = ko.observable('');
    regEmail = ko.observable('');
    regPhoneNumber = ko.observable('');
    regUsername = ko.observable('');
    regPassword = ko.observable('');

    // Observable for error or success message
    message = ko.observable('');

    // Observable for page state
    isLoginPage = ko.observable(true);
    isRegisterPage = ko.observable(false);
    pageTitle = ko.computed(() => this.isLoginPage() ? 'Login Page' : 'Register Page');

    // Methods to show the respective pages
    showLoginPage = () => {
        this.isLoginPage(true);
        this.isRegisterPage(false);
        this.message('');  // Clear any messages
    };

    showRegisterPage = () => {
        this.isLoginPage(false);
        this.isRegisterPage(true);
        this.message('');  // Clear any messages
    };

    // Login method
    login = () => {
        const loginData = {
            username: this.loginUsername(),
            password: this.loginPassword()
        };

        fetch('http://localhost:8080/api/login/authenticate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.authenticated) {
                    this.message('Login successful');
                } else {
                    this.message('Login failed: ' + (data.message || 'Invalid credentials.'));
                }
            })
            .catch(error => {
                this.message('An error occurred during login. Please try again later.');
                console.error('Login error:', error);
            });
    };

    // Registration method
    register = () => {
        const registerData = {
            customerDetail: {
                firstName: this.regFirstName(),
                lastName: this.regLastName(),
                dateOfBirth: this.regDateOfBirth(),
                address: this.regAddress(),
                email: this.regEmail(),
                phoneNumber: this.regPhoneNumber()
            },
            loginDetail: {
                username: this.regUsername(),
                password: this.regPassword()
            }
        };

        fetch('http://localhost:8080/api/customers/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(registerData)
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data) {
                    this.message('Registration successful.');
                    this.showLoginPage();  // Redirect to login page after registration
                } else {
                    this.message(`Registration failed: ${data.message || 'Please try again.'}`);
                }
            })
            .catch(error => {
                console.error('Error:', error);
                this.message('An error occurred. Please try again later.');
            });
    };
}

export = DashboardViewModel;
