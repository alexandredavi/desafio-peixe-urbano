import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {markAllFormFieldAsDirty, markAllFormFieldAsPristine} from '../../utils/form-utils';
import {ActivatedRoute, Router} from '@angular/router';
import {Association} from './association';
import {Deal} from '../deal/deal';
import {BuyOption} from '../buy-option/buy-option';
import {AssociationList} from './association-list';
import {environment} from '../../../environments/environment';

@Component({
  selector: 'app-association-component',
  templateUrl: './association.component.html'
})
export class AssociationComponent implements OnInit {
  association: Association = new Association();
  associations: Array<AssociationList> = [];
  deal: Deal = new Deal();
  buyOptions: Array<BuyOption> = [];

  public form: FormGroup;
  private dealId: string;

  constructor(private httpClient: HttpClient,
              private toastr: ToastrService,
              private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder) {
    this.form = this.formBuilder.group({
      buyOption: [null, [Validators.required]]
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
    const body = JSON.stringify(this.association);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    this.httpClient.post(environment.url + 'deal-buy-option-association', body, {
      headers: headers,
      responseType: 'text'
    }).subscribe(() => {
      this.ngOnInit();
      this.toastr.success('Salvo com sucesso');
      this.association = new Association();
      markAllFormFieldAsPristine(this.form);
    });
  }

  ngOnInit(): void {
    const params: any = this.route.params;
    this.dealId = params.value['id'];
    this.httpClient.get(environment.url + 'deal/' + this.dealId).subscribe((data: Deal) => {
      this.deal = data;
      this.association.dealId = this.dealId;
    });
    this.httpClient.get(environment.url + 'deal-buy-option-association').subscribe((data: Array<AssociationList>) => this.associations = data);
    this.httpClient.get(environment.url + 'buy-option').subscribe((data: Array<BuyOption>) => this.buyOptions = data);
  }

  remove(id: string) {
    this.httpClient.delete(environment.url + 'deal-buy-option-association/' + id).subscribe(() => {
      this.ngOnInit();
      this.toastr.success('Excluido com sucesso');
    });
  }

}
