import { Buyer } from './buyer.model';
import { Payment } from './payment.model';
import { Product } from './products.model';
import { Seller } from './seller.model';

export interface Order {
  id: number;
  moment: string;
  status: string;
  product: Product;
  seller: Seller;
  buyer: Buyer;
  payment?: Payment | null;
}
