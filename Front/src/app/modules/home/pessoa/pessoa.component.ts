import {ListComponent} from "../../../core/components/list.component";
import {Pessoa} from "../../../core/models/pessoa.model";
import {Component, OnInit} from "@angular/core";
import {FormBuilder} from "@angular/forms";
import {NzMessageService} from "ng-zorro-antd";
import {PessoaService} from "../../../core/services/pessoa.service";
import {tap} from "rxjs/operators";


@Component({
  selector: "app-pessoa",
  templateUrl: "./pessoa.component.html",
  styleUrls: ["./pessoa.component.less"]
})
export class PessoaComponent extends ListComponent<Pessoa> implements OnInit {
  constructor(
    private fb: FormBuilder,
    private message: NzMessageService,
    private pessoaService: PessoaService
  ) {
    super();
  }

  ngOnInit() {
    this.buscarPessoas();
  }

  buscarPessoas() {
    this.carregando = true;
    this.pessoaService.findAll().pipe(tap(next => {
      this.dados = next;
      this.carregando = false;
    }, err => {
      console.log(err);
      this.carregando = false;
    })).subscribe();
  }

  abrirForm(t?: Pessoa) {
    if (!t) {
      t = {};
    }

    this.abrirForm$.next(t);
  }

  deletar(id: number) {
    if (!!id) {
      this.pessoaService.delete(id).pipe(tap(_ => {
        this.message.success("Pessoa deletada com sucesso");
        this.buscarPessoas();
      }, err => {
        console.log(err);
        this.message.error("Erro ao deletar pessoa");
      })).subscribe();
    }
  }
}
