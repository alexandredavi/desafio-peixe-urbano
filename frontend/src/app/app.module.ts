import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {PreloadAllModules, RouterModule} from '@angular/router';
import {DealComponent} from './modules/deal/deal.component';
import {ROUTES} from './app.routes';
import {NavbarComponent} from './components/navbar/navbar.component';
import {BuyOptionComponent} from './modules/buy-option/buy-option.component';
import {DealSearchComponent} from './modules/deal-search/deal-search.component';
import {BsDatepickerModule} from 'ngx-bootstrap';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {library} from '@fortawesome/fontawesome-svg-core';
import {faPencilAlt, faTrashAlt} from '@fortawesome/free-solid-svg-icons';
import {DealTypePipePipe} from './modules/deal/deal-type.pipe';
import {ToastrModule} from 'ngx-toastr';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgxCurrencyModule} from 'ngx-currency';

library.add(faPencilAlt, faTrashAlt);

@NgModule({
  declarations: [
    AppComponent,
    BuyOptionComponent,
    DealComponent,
    DealSearchComponent,
    DealTypePipePipe,
    NavbarComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    BsDatepickerModule.forRoot(),
    FontAwesomeModule,
    FormsModule,
    HttpClientModule,
    NgxCurrencyModule,
    ReactiveFormsModule,
    RouterModule.forRoot(ROUTES, {useHash: true, preloadingStrategy: PreloadAllModules}),
    ToastrModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
