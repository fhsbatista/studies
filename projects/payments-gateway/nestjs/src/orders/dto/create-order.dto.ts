import { IsNotEmpty, IsNumber, IsString } from 'class-validator';

export class CreateOrderDto {
  @IsNumber()
  @IsNotEmpty()
  amount: number;

  @IsString()
  @IsNotEmpty()
  credit_card_name: string;

  @IsString()
  @IsNotEmpty()
  credit_card_number: string;

  @IsString()
  @IsNotEmpty()
  credit_card_expiration_month: string;

  @IsString()
  @IsNotEmpty()
  credit_card_expiration_year: string;

  @IsString()
  @IsNotEmpty()
  credit_card_expiration_cvv: string;
}
