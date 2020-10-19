import {NgModule} from "@angular/core";
import {HomeComponent} from "./home.component";
import {RouterModule, Routes} from "@angular/router";
import {CommonModule} from "@angular/common";
import {NzIconModule, NzLayoutModule, NzMenuModule, NzToolTipModule} from "ng-zorro-antd";

const routes: Routes = [{
  path: "",
  component: HomeComponent,
  children: [
    {
      path: "pessoa",
      loadChildren: () => import("./pessoa/pessoa.module").then(m => m.PessoaModule)
    }
  ]
}];

@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    NzLayoutModule,
    NzMenuModule,
    NzIconModule,
    NzToolTipModule,
    RouterModule.forChild(routes)
  ],
  providers: []
})
export class HomeModule {}
