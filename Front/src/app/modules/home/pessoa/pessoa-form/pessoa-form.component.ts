import { Component, OnInit } from "@angular/core";
import { FormComponent } from "../../../../core/components/form.component";
import { Pessoa } from "../../../../core/models/pessoa.model";
import { NzMessageService } from "ng-zorro-antd";
import { FormBuilder, Validators } from "@angular/forms";
import { PessoaService } from "../../../../core/services/pessoa.service";
import { tap } from "rxjs/operators";

@Component({
  selector: "app-pessoa-form",
  templateUrl: "./pessoa-form.component.html",
  styleUrls: ["./pessoa-form.component.less"]
})
export class PessoaFormComponent extends FormComponent<Pessoa> implements OnInit {
  constructor(
    private pessoaService: PessoaService,
    private fb: FormBuilder,
    protected message: NzMessageService
  ) {
    super(message);
    this.initializeForm();
  }

  ngOnInit() {
    this.abrirForm.subscribe(next => {
      if (!next) {
        return;
      }

      this.initializeForm();

      if (!!next.id) {
        this.pessoaService.findOne(next.id).pipe(tap(pessoa => {
          this.formulario.patchValue({...pessoa}, {emitEvent: false});
          this.editando = true;
        }, _ => {
          this.message.error("Erro ao recuperar pessoa");
          this.close();
        })).subscribe();
      }

      this.visivel = true;
    });
  }

  initializeForm() {
    this.formulario = this.fb.group({
      id: [null],
      nome: [null, Validators.required],
      dataNascimento: [new Date(), Validators.required]
    });
  }

  salvar() {
    const pessoa = super.construirObjeto();
    if (!pessoa) {
      return;
    }

    console.log(pessoa);

    this.pessoaService.save(pessoa).pipe(tap(_ => {
      this.message.success("Pessoa salva");
      this.close();
    }, _ => {
      this.message.error("Erro ao salvar pessoa");
      this.salvando = false;
    })).subscribe();
  }

  close() {
    this.initializeForm();
    super.close();
  }
}
