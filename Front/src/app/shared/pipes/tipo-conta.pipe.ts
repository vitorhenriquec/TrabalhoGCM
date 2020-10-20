import { Pipe, PipeTransform } from "@angular/core";

export const TipoConta = {
  CONTA_CORRENTE: {nome: "Conta Corrente"},
  CONTA_POUPANCA: {nome: "Conta Poupança"}
};

export const tiposConta = Object.keys(TipoConta).map(item => ({id: item, text: TipoConta[item]}));

@Pipe({
  name: "tipoConta"
})
export class TipoContaPipe implements PipeTransform {
  transform(value: any, ...args): any {
    if (!value) {
      return value;
    }

    const result = TipoConta[value];
    if (!result) {
      return "Desconhecido";
    }

    return result.nome;
  }
}
