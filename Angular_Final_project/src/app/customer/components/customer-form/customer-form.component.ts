import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-customer-form',
  templateUrl: './customer-form.component.html',
  styleUrls: ['./customer-form.component.scss']
})
export class CustomerFormComponent implements OnInit {

  form!: FormGroup;  // ✅ must declare here
  id!: number;

  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', Validators.required]
    });

    this.id = Number(this.route.snapshot.paramMap.get('id'));
    if(this.id) {
      this.customerService.getCustomerById(this.id).subscribe(res => {
        this.form.patchValue(res);
      });
    }
  }

  submit() {
    if(this.form.invalid) return;

    const request$ = this.id
      ? this.customerService.updateCustomer(this.id, this.form.value)
      : this.customerService.createCustomer(this.form.value);

    request$.subscribe(() => {
      this.router.navigate(['/customers']);
    });
  }
}
