import { Pessoa } from "./pessoa.model";

export interface Conta {
  id?: number;
  tipoConta?: string;
  saldo?: number;
  saldoBonus?: number;
  pessoa?: Pessoa;
}
