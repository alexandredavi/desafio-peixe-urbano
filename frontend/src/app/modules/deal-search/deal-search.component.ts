import {Component, OnInit} from '@angular/core';
import {Deal} from '../deal/deal';
import {HttpClient} from '@angular/common/http';
import {BuyOption} from '../buy-option/buy-option';
import {DealSearch} from './deal-search';
import {ToastrService} from 'ngx-toastr';
import {environment} from '../../../environments/environment';

@Component({
  selector: 'app-deal-search-component',
  templateUrl: './deal-search.component.html'
})
export class DealSearchComponent implements OnInit {
  deals: Array<DealSearch> = [];

  constructor(private httpClient: HttpClient,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.httpClient.get(environment.url + 'deal').subscribe((data: Array<Deal>) => {
      if (data) {
        data.forEach(value => {
          const dealSearch: DealSearch = new DealSearch();
          dealSearch.deal = value;
          this.httpClient.get('http://localhost:8080/deal-buy-option-association/deal/' + value.id)
            .subscribe((buyOptions: Array<BuyOption>) => {
              dealSearch.buyOptions = buyOptions;
            });
          this.deals.push(dealSearch);
        });
      }
    });
  }

  buy(dealId: any, buyOptionId: string) {
    this.httpClient.patch(environment.url + 'buy/' + dealId + '/' + buyOptionId, null).subscribe(() => {
      this.toastr.success('Compra realizada com sucesso!');
    });
  }
}
