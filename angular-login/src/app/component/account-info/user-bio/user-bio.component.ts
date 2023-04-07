import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from '../../../model/user';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-user-bio',
  templateUrl: './user-bio.component.html',
  styleUrls: ['./user-bio.component.scss']
})
export class UserBioComponent implements OnInit {

  accountInfoBioForm: FormGroup = new FormGroup({});
  user: User | undefined;

  emailValidationMessages: string = "";
  private emailValidationMessagesMap: { [key: string]: string } = {
    required: 'Email address is mandatory',
    email: 'Please enter a proper email address'
  };

  constructor(private authService: AuthService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {



    this.authService.user.subscribe({
      next: data => {
        this.user = data;
        console.log('UserBioComponent : user :: ' + JSON.stringify(this.user));
      },
      error: err => {
        console.log('UserBioComponent : Exception :: ' + err);
      }
    })

    this.accountInfoBioForm = this.formBuilder.group({
      userName: { value: this.user?.userName, disabled: true },
      emailAddress: [this.user?.email, [Validators.required, Validators.email]]

    });

    const emailAddressControl = this.accountInfoBioForm.get('emailAddress')
    emailAddressControl?.valueChanges.subscribe(
      value => this.setEmailValidationMessages(emailAddressControl as FormControl)
    )

  }

  private setEmailValidationMessages(emailAddressControl: AbstractControl): void {

    this.emailValidationMessages = "";
    if ((emailAddressControl.touched || emailAddressControl.dirty) && emailAddressControl.errors) {
      this.emailValidationMessages = Object.keys(emailAddressControl.errors).map(
        key => this.emailValidationMessagesMap[key]).join('');
    }

  }

  onSubmit() {
    console.log('UserBioComponent : onSubmit()');
  }

}
