import {Component, OnInit} from "@angular/core";
import {Student} from "./interface/student.interface";
import {StudentService} from "./service/student.service";

@Component({
  selector: "app-student-list",
  templateUrl: "./student-list.component.html",
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {

  students: Student[];

  constructor(private studentService: StudentService) {
    this.students = [];
  }

  ngOnInit() {
    this.studentService.readAllStudents().subscribe(data => {
      this.students = data;
    })
  }

}
