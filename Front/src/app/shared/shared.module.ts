import { NgModule } from "@angular/core";
import { TipoContaPipe } from "./pipes/tipo-conta.pipe";

@NgModule({
  declarations: [
    TipoContaPipe,
    TipoContaPipe
  ],
  imports: [],
  exports: [
    TipoContaPipe,
    TipoContaPipe
  ],
  providers: []
})
export class SharedModule {
  constructor() {}
}
