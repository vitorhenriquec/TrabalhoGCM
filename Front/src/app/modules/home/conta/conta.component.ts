import {Component, Input, OnInit} from "@angular/core";
import { ListComponent } from "../../../core/components/list.component";
import { Conta } from "../../../core/models/conta.model";
import { ContaService } from "../../../core/services/conta.service";
import { FormBuilder } from "@angular/forms";
import { NzMessageService } from "ng-zorro-antd";
import { tap } from "rxjs/operators";
import {BehaviorSubject, Observable} from "rxjs";

@Component({
  selector: "app-conta",
  templateUrl: "./conta.component.html",
  styleUrls: ["./conta.component.less"]
})
export class ContaComponent extends ListComponent<Conta> implements OnInit {

  constructor(
    private fb: FormBuilder,
    private message: NzMessageService,
    private contaService: ContaService
  ) {
    super();
  }

  public abrirTransferenciaForm$ = new BehaviorSubject<Conta>(null);

  public abrirCreditoForm$ = new BehaviorSubject<Conta>(null);

  ngOnInit() {
    this.buscarContas();
  }

  buscarContas() {
    this.carregando = true;
    this.contaService.findAll().pipe(tap(next => {
      this.dados = next;
      this.carregando = false;
    }, err => {
      console.log(err);
      this.carregando = false;
    })).subscribe();
  }

  abrirForm(t?: Conta) {
    if (!t) {
      t = {};
    }

    this.abrirForm$.next(t);
  }

  deletar(id: number) {
    if (!!id) {
      this.contaService.delete(id).pipe(tap(_ => {
        this.message.success("Conta deletada com sucesso");
        this.buscarContas();
      }, err => {
        console.log(err);
        this.message.error("Erro ao deletar conta");
      })).subscribe();
    }
  }

  abrirTransferenciaForm(t: Conta) {
    this.abrirTransferenciaForm$.next(t);
  }

  abrirCreditoForm(t: Conta){
    this.abrirCreditoForm$.next(t);
  }
}
