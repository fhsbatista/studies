import { PartialType } from '@nestjs/mapped-types';
import { CreateOrderDto } from './create-order.dto';
import { OrderStatus } from 'orders/entities/order.entity';

export class UpdateOrderDto extends PartialType(CreateOrderDto) {
  status: OrderStatus;
}
