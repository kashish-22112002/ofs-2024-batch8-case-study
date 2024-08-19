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

    // Observable properties for transaction form
    senderAccountId = ko.observable('');
    receiverAccountId = ko.observable('');
    transferAmount = ko.observable('');

    // Observable for transaction result message
    transactionMessage = ko.observable('');
    
    // Observable for page state
    isLoginPage = ko.observable(true);
    isRegisterPage = ko.observable(false);
    isTransactionPage = ko.observable(false);

    // Modify pageTitle to reflect current page
    pageTitle = ko.computed(() => {
        if (this.isLoginPage()) return 'Login Page';
        if (this.isRegisterPage()) return 'Register Page';
        if (this.isTransactionPage()) return 'Transaction Page';
        return 'Dashboard';
    });

    // Computed observable to enable/disable the register button
    isRegisterButtonEnabled = ko.computed(() => {
        return this.regFirstName() && this.regLastName() && this.regDateOfBirth() &&
               this.regAddress() && this.regEmail() && this.regPhoneNumber() &&
               this.regUsername() && this.regPassword();
    });

    // Computed observable to enable/disable the login button
    isLoginButtonEnabled = ko.computed(() => {
        return this.loginUsername() && this.loginPassword();
    });

    // Computed observable to enable/disable the transaction button
    isTransactionButtonEnabled = ko.computed(() => {
        return this.senderAccountId() && this.receiverAccountId() && this.transferAmount();
    });

    // Methods to show the respective pages
    showLoginPage = () => {
        this.isLoginPage(true);
        this.isRegisterPage(false);
        this.isTransactionPage(false);
        this.message('');  // Clear any messages
    };

    showRegisterPage = () => {
        this.isLoginPage(false);
        this.isRegisterPage(true);
        this.isTransactionPage(false);
        this.message('');  // Clear any messages
    };

    showTransactionPage = () => {
        this.isLoginPage(false);
        this.isRegisterPage(false);
        this.isTransactionPage(true);
        this.message('');  // Clear login/register messages
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
                    this.showTransactionPage();
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
                this.message('Registration successful.');
                this.showLoginPage();  // Redirect to login page after registration
            })
            .catch(error => {
                console.error('Error:', error);
                this.message('An error occurred. Please try again later.');
            });
    };

    // Transaction method
    performTransaction = () => {
        // Use the correct field names for the API
        const transactionData = {
            fromAccountId: this.senderAccountId(),
            toAccountId: this.receiverAccountId(),
            amount: this.transferAmount()
        };
    
        console.log('Transaction Data:', transactionData); // Log the transaction data
    
        fetch('http://localhost:8080/api/transactions/transfer', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(transactionData)
        })
        .then(response => {
            if (!response.ok) {
                console.error(`HTTP error! status: ${response.status}`);
                return response.text(); // Read the response text to include in the error message
            }
            return response.text(); // Read the response text for parsing
        })
        .then(text => {
            if (text) {
                try {
                    const data = JSON.parse(text);
                    this.transactionMessage('Transaction successful!');
                    
                } catch (e) {
                    // Handle cases where the response is not valid JSON
                    this.transactionMessage('Transaction failed: Invalid response format.');
                }
            } else {
                this.transactionMessage('Transaction failed: Empty response from server.');
            }
        })
        .catch(error => {
            console.error('Transaction error:', error);
            this.transactionMessage('An error occurred during the transaction. Please try again later.');
        });
    };
}

export = DashboardViewModel;
