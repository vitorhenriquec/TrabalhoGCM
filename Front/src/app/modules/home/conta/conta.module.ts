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
    ContaFormComponent
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
    NgZorroAntdModule
  ],
  providers: [
    ContaService,
    PessoaService
  ]
})
export class ContaModule {
  constructor() {
  }
}
