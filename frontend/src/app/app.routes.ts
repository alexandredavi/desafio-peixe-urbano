import {Routes} from '@angular/router';
import {DealComponent} from './modules/deal/deal.component';
import {BuyOptionComponent} from './modules/buy-option/buy-option.component';
import {DealSearchComponent} from './modules/deal-search/deal-search.component';

export const ROUTES: Routes = [
  {
    path: '',
    redirectTo: 'deal',
    pathMatch: 'full'
  },
  {
    path: 'deal',
    component: DealComponent
  },
  {
    path: 'buy-option',
    component: BuyOptionComponent
  },
  {
    path: 'deal-search',
    component: DealSearchComponent
  }
];
