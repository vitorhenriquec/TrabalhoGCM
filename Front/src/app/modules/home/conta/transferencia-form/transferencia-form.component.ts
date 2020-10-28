import { Component, OnInit } from "@angular/core";
import { FormComponent } from "../../../../core/components/form.component";
import { Conta } from "../../../../core/models/conta.model";
import { NzMessageService } from "ng-zorro-antd";
import { ContaService } from "../../../../core/services/conta.service";
import { FormBuilder, Validators } from "@angular/forms";
import {TipoContaPipe, tiposConta} from "../../../../shared/pipes/tipo-conta.pipe";
import { tap } from "rxjs/operators";

@Component({
  selector: "app-transferencia-form",
  templateUrl: "./transferencia-form.component.html",
  styleUrls: ["./transferencia-formcomponent.less"]
})
export class TransferenciaFormComponent extends FormComponent<Conta> implements OnInit {

  public tiposContas = tiposConta;
  public contas: Conta[] = [];

  constructor(
    private pipe: TipoContaPipe,
    private contaService: ContaService,
    protected message: NzMessageService,
    private fb: FormBuilder
  ) {
    super(message);
    this.initializeForm();
    this.buscarContas();
  }

  ngOnInit() {
    this.abrirForm.subscribe(next => {
      if (!next) {
        return;
      }

      this.initializeForm();
      this.formulario.get('id').setValue(next.id);
      this.visivel = true;
    });
  }

  initializeForm() {
    this.formulario = this.fb.group({
      id: [null],
      valor: [0],
      conta: [null, Validators.required]
    });

    this.formulario.valueChanges.subscribe(next => {
      if (!this.formulario.get("id").value) {
        this.formulario.get("pessoa").enable({emitEvent: false});
      }
    });
  }

  buscarContas() {
    this.contaService.findAll().pipe(tap(next => {
      this.contas = next;
    })).subscribe();
  }

  transferir() {
    const informacoes = this.formulario.getRawValue();

    this.salvando = true;

    const transferencia = {
      idConta: informacoes.id,
      idContaDestino: informacoes.conta,
      valor: informacoes.valor
    };

    this.contaService.transfer(transferencia).pipe(tap(next => {
      this.message.success("Transferência feita com sucesso");
      this.close();
    }, error => {
      this.message.error("Erro ao fazer a transferência");
      this.salvando = false;
    })).subscribe();
  }

  close() {
    this.initializeForm();
    super.close();
  }

  recuperarLabel(conta: Conta) {
    return conta.pessoa.nome + " - " + this.pipe.transform(conta.tipoConta);
  }

  filtrarContas() {
    const contaSelecionadaId = this.formulario.get('id').value;

    return this.contas.filter((conta) => {
      return conta.id !== contaSelecionadaId;
    });
  }
}

