import {Component, OnInit} from '@angular/core';
import {Deal} from './deal';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {markAllFormFieldAsDirty, markAllFormFieldAsPristine} from '../../utils/form-utils';

@Component({
  selector: 'app-deal-component',
  templateUrl: './deal.component.html'
})
export class DealComponent implements OnInit {
  deal: Deal = new Deal();
  deals: Array<Deal> = [];

  public form: FormGroup;

  constructor(private httpClient: HttpClient,
              private toastr: ToastrService,
              private formBuilder: FormBuilder) {
    this.form = this.formBuilder.group({
      dealTitle: [null, [Validators.required]],
      dealText: [null, [Validators.required]],
      dealType: [null, [Validators.required]],
      publishDate: [null, [Validators.required]],
      dealValidity: [null, [Validators.required]]
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
    if (this.deal.id) {
      this.httpClient.put('http://localhost:8080/deal/' + this.deal.id, this.deal).subscribe(() => {
        this.ngOnInit();
        this.toastr.success('Salvo com sucesso');
        this.deal = new Deal();
        markAllFormFieldAsPristine(this.form);
      });
    } else {
      const body = JSON.stringify(this.deal);
      const headers = new HttpHeaders({
        'Content-Type': 'application/json'
      });
      this.httpClient.post('http://localhost:8080/deal', body, {headers: headers, responseType: 'text'}).subscribe(() => {
        this.ngOnInit();
        this.toastr.success('Salvo com sucesso');
        this.deal = new Deal();
        markAllFormFieldAsPristine(this.form);
      });
    }
  }

  ngOnInit(): void {
    this.httpClient.get('http://localhost:8080/deal').subscribe((data: Array<Deal>) => this.deals = data);
  }

  edit(deal: Deal) {
    this.deal = deal;
    this.deal.publishDate = new Date(this.deal.publishDate);
  }

  remove(id: string) {
    this.httpClient.delete('http://localhost:8080/deal/' + id).subscribe(() => {
      if (this.deal.id === id) {
        this.deal = new Deal();
        markAllFormFieldAsPristine(this.form);
      }
      this.ngOnInit();
      this.toastr.success('Excluido com sucesso');
    });
  }
}
