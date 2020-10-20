import { NgModule } from "@angular/core";
import { TipoContaPipe } from "./pipes/tipo-conta.pipe";

@NgModule({
  declarations: [
    TipoContaPipe
  ],
  imports: [],
  exports: [
    TipoContaPipe
  ],
  providers: []
})
export class SharedModule {
  constructor() {}
}
