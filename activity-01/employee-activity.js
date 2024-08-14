import fs from 'fs';
import readlineSync from 'readline-sync';
const path = './employees.json';

//read the current employee data from the JSON file
function readEmployeeData() {
    if (fs.existsSync(path)) {
        const data = fs.readFileSync(path, 'utf-8');
        return JSON.parse(data);
    } else {
        return [];
    }
}

//write employee data to the JSON file
function writeEmployeeData(data) {
    fs.writeFileSync(path, JSON.stringify(data, null, 2), 'utf-8');
}

//add or update an employee
function addOrUpdateEmployee(id, name, salary) {
    const employees = readEmployeeData();
    
    //if employee with the same ID exists
    const index = employees.findIndex(emp => emp.id === id);
    
    if (index >= 0) {
        //update existing employee
        employees[index] = { id, name, salary };
    } else {
        //add new employee
        employees.push({ id, name, salary });
    }
    writeEmployeeData(employees);
}

//print all employee details
function printAllEmployees() {
    const employees = readEmployeeData();
    console.log("Employee Details:");
    employees.forEach(emp => {
        console.log(`ID: ${emp.id}, Name: ${emp.name}, Salary: ${emp.salary}`);
    });
}

//prompt user for input and handle it
function promptUser() {
    const id = readlineSync.question('Enter Employee ID: ');
    const name = readlineSync.question('Enter Employee Name: ');
    const salaryNumber = readlineSync.questionFloat('Enter Employee Salary: ');

    if (isNaN(salaryNumber)) {
        console.log('Salary must be a number.');
        return;
    }

    addOrUpdateEmployee(id, name, salaryNumber);
    printAllEmployees();
}

promptUser();