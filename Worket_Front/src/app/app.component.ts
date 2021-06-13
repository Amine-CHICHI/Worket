import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Employee } from './employee';
import { EmployeeService } from './employee.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  public employees: Employee[] = [];
  public editEmployee?: Employee;
  public email: string = '';

  constructor(private employeeService: EmployeeService) {}

  ngOnInit() {
    this.getEmployees();
  }
  public getEmployees(): void {
    this.employeeService.getEmployees().subscribe(
      (response: Employee[]) => {
        this.employees = response;
        // console.log(this.employees);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddForm(addForm: NgForm): void {
    document.getElementById('add-employee-form')?.click();
    this.employeeService.addEmployees(addForm.value).subscribe(
      (response: Employee) => {
        console.log(response);
        this.getEmployees();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateForm(updateForm: NgForm): void {
    document.getElementById('update-employee-form')?.click();
    this.employeeService.updateEmployees(updateForm.value).subscribe(
      (response: Employee) => {
        console.log(response);
        this.getEmployees();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteModal(employeID?: number): void {
    const id = employeID || 0;
    this.employeeService.deleteEmployees(id).subscribe(
      (response: void) => {
        this.getEmployees();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onOpenModal(mode: String, employee?: Employee): void {
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    switch (mode) {
      case 'add': {
        button.setAttribute('data-target', '#addEmployeeModal');
        break;
      }
      case 'update': {
        button.setAttribute('data-target', '#updateEmployeeModal');
        this.editEmployee = employee;
        break;
      }
      case 'delete': {
        button.setAttribute('data-target', '#deleteEmployeeModal');
        this.editEmployee = employee;
        break;
      }
    }
    const container = document.getElementById('main-container');
    container?.appendChild(button);
    button.click();
  }
}
