import { Pessoa } from "./pessoa.model";

export interface Conta {
  id?: number;
  tipoConta?: string;
  saldo?: number;
  pessoa?: Pessoa;
}
