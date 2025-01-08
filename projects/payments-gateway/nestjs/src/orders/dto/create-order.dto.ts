import { IsInt, IsNotEmpty, IsNumber, IsString } from 'class-validator';

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

  @IsInt()
  @IsNotEmpty()
  credit_card_expiration_month: string;

  @IsInt()
  @IsNotEmpty()
  credit_card_expiration_year: string;

  @IsInt()
  @IsNotEmpty()
  credit_card_expiration_cvv: string;
}
