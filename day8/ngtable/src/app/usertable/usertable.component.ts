import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-usertable',
  templateUrl: './usertable.component.html',
  styleUrls: ['./usertable.component.css']
})
export class UsertableComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  users = [
    { name: "Jeremie", age: 21, gender: "Male" },
    { name: "Justin", age: 19, gender: "Male" },
    { name: "Kimberly", age: 44, gender: "Female" },
    { name: "Joshua", age: 23, gender: "Male" },
    { name: "Anna", age: 28, gender: "Female" },
  ];
}
