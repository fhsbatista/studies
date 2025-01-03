import { Injectable } from '@nestjs/common';
import { CreateAccountDto } from './dto/create-account.dto';
import { UpdateAccountDto } from './dto/update-account.dto';
import { Account } from './entities/account.entity';
import { InjectModel } from '@nestjs/sequelize';

@Injectable()
export class AccountsService {
  constructor(
    @InjectModel(Account)
    private accountModel: typeof Account,
  ) {}

  create(createAccountDto: CreateAccountDto) {
    return this.accountModel.create({ ...createAccountDto });
  }

  findAll() {
    return this.accountModel.findAll();
  }

  findOne(id: number) {
    return this.accountModel.findByPk(id);
  }

  async update(id: number, updateAccountDto: UpdateAccountDto) {
    const account = await this.accountModel.findByPk(id);
    return account.update(updateAccountDto);
  }

  async remove(id: number) {
    const account = await this.accountModel.findByPk(id);
    return account.destroy();
  }
}
