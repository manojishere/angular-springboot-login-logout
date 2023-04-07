import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../../../model/user';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-user-phone',
  templateUrl: './user-phone.component.html',
  styleUrls: ['./user-phone.component.scss']
})
export class UserPhoneComponent implements OnInit {

  accountInfoPhoneForm: FormGroup = new FormGroup({});
  user: User | undefined;

  constructor(private authService: AuthService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {

    this.authService.user.subscribe({
      next: data => {
        this.user = data;
        console.log('UserPhoneComponent : user :: ' + JSON.stringify(this.user));
      },
      error: err => {
        console.log('UserPhoneComponent : Exception :: ' + err);
      }
    })

    this.accountInfoPhoneForm = this.formBuilder.group({
      phoneNumber: this.user?.phoneNumber,
      phoneNumber2: [this.user?.phoneNumber2, [Validators.required, Validators.minLength(10), Validators.maxLength(10)]]

    });

  }

  onSubmit() {
    console.log('UserPhoneComponent : onSubmit()');
  }

}
