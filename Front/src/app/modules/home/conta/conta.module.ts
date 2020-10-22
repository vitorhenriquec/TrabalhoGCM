import { RouterModule, Routes } from "@angular/router";
import { ContaComponent } from "./conta.component";
import { NgModule } from "@angular/core";
import { ContaService } from "../../../core/services/conta.service";
import {
  NgZorroAntdModule,
  NzButtonModule,
  NzDrawerModule,
  NzFormModule,
  NzGridModule,
  NzIconModule,
  NzInputModule,
  NzPopconfirmModule,
  NzSelectModule,
  NzSpinModule,
  NzTableModule,
  NzToolTipModule
} from "ng-zorro-antd";
import { CommonModule } from "@angular/common";
import { SharedModule } from "../../../shared/shared.module";
import { ContaFormComponent } from "./conta-form/conta-form.component";
import { ReactiveFormsModule } from "@angular/forms";
import { PessoaService } from "../../../core/services/pessoa.service";
import { TransferenciaFormComponent } from "./transferencia-form/transferencia-form.component";
import { CreditoFormComponent } from "./credito-form/credito-form.component";
import { DebitoFormComponent } from "./debito-form/debito-form.component";
import {TipoContaPipe} from "../../../shared/pipes/tipo-conta.pipe";
import {NgxCurrencyModule} from "ngx-currency";


const routes: Routes = [
  {
    path: "",
    component: ContaComponent,
    children: []
  }
];

@NgModule({
  declarations: [
    ContaComponent,
    ContaFormComponent,
    TransferenciaFormComponent,
    CreditoFormComponent,
    DebitoFormComponent
  ],
  imports: [
    RouterModule.forChild(routes),
    NzGridModule,
    NzButtonModule,
    NzTableModule,
    NzPopconfirmModule,
    CommonModule,
    SharedModule,
    NzDrawerModule,
    NzSpinModule,
    NzFormModule,
    NzInputModule,
    ReactiveFormsModule,
    NzSelectModule,
    NzIconModule,
    NzToolTipModule,
    NgZorroAntdModule,
    NgxCurrencyModule,
  ],
  providers: [
    ContaService,
    PessoaService,
    TipoContaPipe,
  ]
})
export class ContaModule {
  constructor() {
  }
}
