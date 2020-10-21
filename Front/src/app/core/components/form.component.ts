import {FormGroup} from "@angular/forms";
import {EventEmitter, Input, Output} from "@angular/core";
import {Observable} from "rxjs";
import {NzMessageService} from "ng-zorro-antd";

export abstract class FormComponent<T> {
  // Formulário
  public formulario: FormGroup;

  // Booleans
  public visivel = false;
  public salvando = false;
  public editando = false;

  // MessageBus
  @Input() abrirForm: Observable<T>;
  @Output() emitEvent = new EventEmitter();

  constructor(
    protected message: NzMessageService
  ) {
  }

  construirObjeto() {
    Object.values(this.formulario.controls).forEach(it => {
      it.markAsDirty();
      it.updateValueAndValidity();
    });

    if (this.formulario.invalid) {
      this.message.error("Formulário Inválido");
      return null;
    }

    const params = this.formulario.getRawValue();
    this.salvando = true;
    const objeto: T = {
      ...params
    };

    return objeto;
  }

  close(emit?) {
    this.visivel = false;
    this.salvando = false;
    this.editando = false;
    this.emitEvent.emit(emit);
  }
}
