import {Pipe, PipeTransform} from '@angular/core';

@Pipe({name: 'dealTypePipe'})
export class DealTypePipePipe implements PipeTransform {
  transform(value: string, ...args: any[]): any {
    if (value === 'LOCAL') {
      return 'Local';
    }
    if (value === 'PRODUCT') {
      return 'Produto';
    }
    return 'Viagem';
  }

}
