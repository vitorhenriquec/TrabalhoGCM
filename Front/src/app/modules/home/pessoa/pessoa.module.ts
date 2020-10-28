import { NgModule } from "@angular/core";
import { PessoaComponent } from "./pessoa.component";
import { PessoaService } from "../../../core/services/pessoa.service";
import { RouterModule, Routes } from "@angular/router";
import {
  NzButtonModule,
  NzDatePickerModule,
  NzDrawerModule,
  NzFormModule,
  NzGridModule,
  NzInputModule, NzPopconfirmModule,
  NzSpinModule,
  NzTableModule
} from "ng-zorro-antd";
import { CommonModule } from "@angular/common";
import { PessoaFormComponent } from "./pessoa-form/pessoa-form.component";
import { ReactiveFormsModule } from "@angular/forms";

const routes: Routes = [
  {
    path: "",
    component: PessoaComponent,
    children: []
  }
];

@NgModule({
  declarations: [
    PessoaComponent,
    PessoaFormComponent
  ],
  imports: [
    RouterModule.forChild(routes),
    NzTableModule,
    CommonModule,
    NzButtonModule,
    NzGridModule,
    NzDrawerModule,
    NzSpinModule,
    NzFormModule,
    ReactiveFormsModule,
    NzInputModule,
    NzDatePickerModule,
    NzPopconfirmModule
  ],
  providers: [
    PessoaService
  ]
})
export class PessoaModule {
  constructor() {
  }
}
