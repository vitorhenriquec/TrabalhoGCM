import { Component, OnInit } from "@angular/core";
import { FormComponent } from "../../../../core/components/form.component";
import { Conta } from "../../../../core/models/conta.model";
import { NzMessageService } from "ng-zorro-antd";
import { ContaService } from "../../../../core/services/conta.service";
import { FormBuilder, Validators } from "@angular/forms";
import { tiposConta } from "../../../../shared/pipes/tipo-conta.pipe";
import { Pessoa } from "../../../../core/models/pessoa.model";
import { PessoaService } from "../../../../core/services/pessoa.service";
import { tap } from "rxjs/operators";
import {Transferencia} from "../../../../core/models/transferencia.models";

@Component({
  selector: "app-conta-form",
  templateUrl: "./conta-form.component.html",
  styleUrls: ["./conta-form.component.less"]
})
export class ContaFormComponent extends FormComponent<Conta> implements OnInit {

  public tiposContas = tiposConta;
  public pessoas: Pessoa[] = [];

  constructor(
    private pessoaService: PessoaService,
    private contaService: ContaService,
    protected message: NzMessageService,
    private fb: FormBuilder
  ) {
    super(message);
    this.initializeForm();
    this.buscarPessoas();
  }

  ngOnInit() {
    this.abrirForm.subscribe(next => {
      if (!next) {
        return;
      }

      this.initializeForm();

      if (!!next.id) {
        this.contaService.findOne(next.id).pipe(tap(conta => {
          this.formulario.patchValue({...conta}, {emitEvent: false});
          const pessoa = this.pessoas.find(it => (it.id === conta.pessoa.id));
          this.formulario.get("pessoa").setValue(pessoa, {emitEvent: false});
          this.formulario.get("pessoa").disable({emitEvent: false});
          this.editando = true;
        }, _ => {
          this.message.error("Erro ao recuperar pessoa");
          this.close();
        })).subscribe();
      } else {
        this.formulario.get("pessoa").enable();
      }

      this.visivel = true;
    });
  }

  initializeForm() {
    this.formulario = this.fb.group({
      id: [null],
      tipoConta: [null, Validators.required],
      saldo: [0],
      pessoa: [null, Validators.required]
    });

    this.formulario.valueChanges.subscribe(next => {
      if (!this.formulario.get("id").value) {
        this.formulario.get("pessoa").enable({emitEvent: false});
      }
    });
  }

  buscarPessoas() {
    this.pessoaService.findAll().pipe(tap(next => {
      this.pessoas = next;
    })).subscribe();
  }

  salvar() {
    const conta = super.construirObjeto();
    if (!conta) {
      return;
    }

    console.log(conta);

    this.contaService.save(conta).pipe(tap(_ => {
      this.message.success("Conta salva");
      this.close();
    }, err => {
      console.log(err);
      this.message.error("Erro ao salvar conta");
      this.salvando = false;
    })).subscribe();
  }

  close() {
    this.initializeForm();
    super.close();
  }
}
