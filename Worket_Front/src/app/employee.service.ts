import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from './employee';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  private apiServerUrl: String = environment.apiBaseUrl;
  constructor(private http: HttpClient) {}

  public getEmployees(): Observable<Employee[]> {
    console.log(`${this.apiServerUrl}/employee/all`);
    return this.http.get<Employee[]>(`${this.apiServerUrl}/employee/all`);
  }

  public addEmployees(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(
      `${this.apiServerUrl}/employee/add`,
      employee
    );
  }

  public updateEmployees(employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(
      `${this.apiServerUrl}/employee/update/${employee.id}`,
      employee
    );
  }

  public deleteEmployees(employeeID: number): Observable<void> {
    return this.http.delete<void>(
      `${this.apiServerUrl}/employee/delete/${employeeID}`
    );
  }
}
