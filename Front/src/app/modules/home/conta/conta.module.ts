import { RouterModule, Routes } from "@angular/router";
import { ContaComponent } from "./conta.component";
import { NgModule } from "@angular/core";
import { ContaService } from "../../../core/services/conta.service";
import { NzButtonModule, NzGridModule, NzPopconfirmModule, NzTableModule } from "ng-zorro-antd";
import { CommonModule } from "@angular/common";
import { SharedModule } from "../../../shared/shared.module";

const routes: Routes = [
  {
    path: "",
    component: ContaComponent,
    children: []
  }
];

@NgModule({
  declarations: [
    ContaComponent
  ],
  imports: [
    RouterModule.forChild(routes),
    NzGridModule,
    NzButtonModule,
    NzTableModule,
    NzPopconfirmModule,
    CommonModule,
    SharedModule
  ],
  providers: [
    ContaService
  ]
})
export class ContaModule {
  constructor() {}
}
