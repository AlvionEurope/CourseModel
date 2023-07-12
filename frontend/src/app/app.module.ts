import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {StudentService} from "./service/student.service";
import {HttpClientModule} from "@angular/common/http";
import {StudentListComponent} from "./student-list.component";

@NgModule({
  declarations: [
    AppComponent,
    StudentListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [StudentService],
  bootstrap: [StudentListComponent]
})
export class AppModule { }
