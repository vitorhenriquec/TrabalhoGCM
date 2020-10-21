import {FormGroup} from "@angular/forms";
import {BehaviorSubject} from "rxjs";

export abstract class ListComponent<T> {
  // Filtro Form
  public filtroForm: FormGroup;

  // Estrutura de Dados
  public dados: T[];

  // Booleanos
  public carregando = false;

  // MessageBus
  public abrirForm$ = new BehaviorSubject<T>(null);

  constructor() {}

  public abstract abrirForm(t?: T);
}
