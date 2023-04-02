import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-account-info-contact',
  templateUrl: './account-info-contact.component.html',
  styleUrls: ['./account-info-contact.component.scss']
})
export class AccountInfoContactComponent implements OnInit {
  accountInfoContactForm: FormGroup = new FormGroup({});
  user: User | undefined;

  constructor(private authService: AuthService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {



    this.authService.user.subscribe({
      next: data => {
        this.user = data;
        console.log('AccountInfoContactComponent : user :: ' + JSON.stringify(this.user));
      },
      error: err => {
        console.log('AccountInfoContactComponent : Exception :: ' + err);
      }
    })

    this.accountInfoContactForm = this.formBuilder.group({
      userName: { value: this.user?.userName, disabled:true },
      emailAddress: [this.user?.email, [Validators.required, Validators.email]]
    });
    
  }

  onSubmit() {
    console.log('AccountInfoContactComponent : onSubmit()');
  }


}
