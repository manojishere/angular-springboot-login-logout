import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
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
  hasUnitNumber: boolean = false;

  states = [
    { name: "Alabama", abbreviation: "AL" },
    { name: "Alaska", abbreviation: "AK" },
    { name: "American Samoa", abbreviation: "AS" },
    { name: "Arizona", abbreviation: "AZ" },
    { name: "Arkansas", abbreviation: "AR" },
    { name: "California", abbreviation: "CA" },
    { name: "Colorado", abbreviation: "CO" },
    { name: "Connecticut", abbreviation: "CT" },
    { name: "Delaware", abbreviation: "DE" },
    { name: "District Of Columbia", abbreviation: "DC" },
    { name: "Federated States Of Micronesia", abbreviation: "FM" },
    { name: "Florida", abbreviation: "FL" },
    { name: "Georgia", abbreviation: "GA" },
    { name: "Guam", abbreviation: "GU" },
    { name: "Hawaii", abbreviation: "HI" },
    { name: "Idaho", abbreviation: "ID" },
    { name: "Illinois", abbreviation: "IL" },
    { name: "Indiana", abbreviation: "IN" },
    { name: "Iowa", abbreviation: "IA" },
    { name: "Kansas", abbreviation: "KS" },
    { name: "Kentucky", abbreviation: "KY" },
    { name: "Louisiana", abbreviation: "LA" },
    { name: "Maine", abbreviation: "ME" },
    { name: "Marshall Islands", abbreviation: "MH" },
    { name: "Maryland", abbreviation: "MD" },
    { name: "Massachusetts", abbreviation: "MA" },
    { name: "Michigan", abbreviation: "MI" },
    { name: "Minnesota", abbreviation: "MN" },
    { name: "Mississippi", abbreviation: "MS" },
    { name: "Missouri", abbreviation: "MO" },
    { name: "Montana", abbreviation: "MT" },
    { name: "Nebraska", abbreviation: "NE" },
    { name: "Nevada", abbreviation: "NV" },
    { name: "New Hampshire", abbreviation: "NH" },
    { name: "New Jersey", abbreviation: "NJ" },
    { name: "New Mexico", abbreviation: "NM" },
    { name: "New York", abbreviation: "NY" },
    { name: "North Carolina", abbreviation: "NC" },
    { name: "North Dakota", abbreviation: "ND" },
    { name: "Northern Mariana Islands", abbreviation: "MP" },
    { name: "Ohio", abbreviation: "OH" },
    { name: "Oklahoma", abbreviation: "OK" },
    { name: "Oregon", abbreviation: "OR" },
    { name: "Palau", abbreviation: "PW" },
    { name: "Pennsylvania", abbreviation: "PA" },
    { name: "Puerto Rico", abbreviation: "PR" },
    { name: "Rhode Island", abbreviation: "RI" },
    { name: "South Carolina", abbreviation: "SC" },
    { name: "South Dakota", abbreviation: "SD" },
    { name: "Tennessee", abbreviation: "TN" },
    { name: "Texas", abbreviation: "TX" },
    { name: "Utah", abbreviation: "UT" },
    { name: "Vermont", abbreviation: "VT" },
    { name: "Virgin Islands", abbreviation: "VI" },
    { name: "Virginia", abbreviation: "VA" },
    { name: "Washington", abbreviation: "WA" },
    { name: "West Virginia", abbreviation: "WV" },
    { name: "Wisconsin", abbreviation: "WI" },
    { name: "Wyoming", abbreviation: "WY" }
  ];

  emailValidationMessages: string = "";
  private emailValidationMessagesMap : { [key: string]: string } = {
    required: 'Email address is mandatory',
    email: 'Please enter a proper email address'
  };

  address1ValidationMessages: string = "";

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
      emailAddress: [this.user?.email, [Validators.required, Validators.email]],
      address1: [this.user?.address?.address1, [Validators.required, Validators.minLength(15)]],
      address2: [this.user?.address?.address2],
      state: [this.user?.address?.state, [Validators.required]],
      city: [this.user?.address?.city, [Validators.required]],
      postalCode: [this.user?.address?.postalCode, [Validators.required]]

    });

    const emailAddressControl = this.accountInfoContactForm.get('emailAddress')
    emailAddressControl?.valueChanges.subscribe(
      value => this.setEmailValidationMessages(emailAddressControl as FormControl )
    )

    const address1Control = this.accountInfoContactForm.get('address1')
    address1Control?.valueChanges.subscribe(
      value => this.setAddress1ValidationMessages(address1Control)
    )
    
  }

  private setAddress1ValidationMessages(c: AbstractControl): void {
    this.address1ValidationMessages = "";
    if (c.errors && (c.touched || c.dirty)) {
      if (c.errors?.required) {
        this.address1ValidationMessages = 'Adress1 is required field';
      } else if (c.errors?.minlength) {
        this.address1ValidationMessages = 'Minimum length is ' + c.errors?.minlength?.requiredLength;
      }
    }
  }

  private setEmailValidationMessages(emailAddressControl: AbstractControl): void {

    this.emailValidationMessages = "";
    if ((emailAddressControl.touched || emailAddressControl.dirty) && emailAddressControl.errors) {
      this.emailValidationMessages = Object.keys(emailAddressControl.errors).map(
        key => this.emailValidationMessagesMap[key]).join('');
    }

  }

  onSubmit() {
    console.log('AccountInfoContactComponent : onSubmit()');
  }


}
