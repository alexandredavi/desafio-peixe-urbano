import {Deal} from '../deal/deal';
import {BuyOption} from '../buy-option/buy-option';

export class DealSearch {
  deal: Deal;
  buyOptions: Array<BuyOption> = [];
  selectedBuyOptionId: string;
}
