import { Observable } from "rxjs";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Student} from "../interface/student.interface";
import {environment} from "../../env/environment";

@Injectable({
    providedIn: "root"
  })

export class StudentService {
  public readonly apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  public createStudent(student: Student): Observable<number> {
    return this.http.post<number>("http://localhost:8080/students", student);
  }

  public readStudentById(id: number): Observable<Student> {
    return this.http.get<Student>("${this.apiServerUrl}/students/${id}")
  }

  public readAllStudents(): Observable<Student[]> {
    return this.http.get<Student[]>("http://localhost:8080/api/v1/students")
  }

  public updateStudentById(student: Student): Observable<boolean> {
    return this.http.put<boolean>("${this.apiServerUrl}/students", student);
  }

  public deleteStudentById(id: number): Observable<boolean> {
    return this.http.delete<boolean>("${this.apiServerUrl}/students");
  }
}
