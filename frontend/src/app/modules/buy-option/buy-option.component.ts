import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {markAllFormFieldAsDirty, markAllFormFieldAsPristine} from '../../utils/form-utils';
import {BuyOption} from './buy-option';
import {environment} from '../../../environments/environment';

@Component({
  selector: 'app-buy-option-component',
  templateUrl: './buy-option.component.html'
})
export class BuyOptionComponent implements OnInit {
  buyOption: BuyOption = new BuyOption();
  buyOptions: Array<BuyOption> = [];

  public form: FormGroup;

  constructor(private httpClient: HttpClient,
              private toastr: ToastrService,
              private formBuilder: FormBuilder) {
    this.form = this.formBuilder.group({
      title: [null, [Validators.required]],
      normalPrice: [null, [Validators.required]],
      startDate: [null, [Validators.required]],
      endDate: [null, [Validators.required]]
    });
  }

  submit() {
    if (this.form.invalid) {
      markAllFormFieldAsDirty(this.form);
      this.toastr.error('Dados ivalidos', 'Verifique o formulario');
    } else {
      this.save();
    }
  }

  private save() {
    if (this.buyOption.id) {
      this.httpClient.put(environment.url + 'buy-option/' + this.buyOption.id, this.buyOption).subscribe(() => {
        this.ngOnInit();
        this.toastr.success('Salvo com sucesso');
        this.buyOption = new BuyOption();
        markAllFormFieldAsPristine(this.form);
      });
    } else {
      const body = JSON.stringify(this.buyOption);
      const headers = new HttpHeaders({
        'Content-Type': 'application/json'
      });
      this.httpClient.post(environment.url + 'buy-option', body, {headers: headers, responseType: 'text'}).subscribe(() => {
        this.ngOnInit();
        this.toastr.success('Salvo com sucesso');
        this.buyOption = new BuyOption();
        markAllFormFieldAsPristine(this.form);
      });
    }
  }

  ngOnInit(): void {
    this.httpClient.get(environment.url + 'buy-option').subscribe((data: Array<BuyOption>) => this.buyOptions = data);
  }

  edit(buyOption: BuyOption) {
    this.buyOption = buyOption;
    this.buyOption.startDate = new Date(this.buyOption.startDate);
    this.buyOption.endDate = new Date(this.buyOption.endDate);
  }

  remove(id: string) {
    this.httpClient.delete(environment.url + 'buy-option/' + id).subscribe(() => {
      if (this.buyOption.id === id) {
        this.buyOption = new BuyOption();
        markAllFormFieldAsPristine(this.form);
      }
      this.ngOnInit();
      this.toastr.success('Excluido com sucesso');
    });
  }

  calculateSalePrice() {
    if (this.buyOption.percentageDiscount) {
      this.buyOption.salePrice = this.buyOption.normalPrice - (this.buyOption.normalPrice * this.buyOption.percentageDiscount / 100);
    } else {
      this.buyOption.salePrice = this.buyOption.normalPrice;
    }
  }
}
