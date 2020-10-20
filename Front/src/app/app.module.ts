import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppComponent } from "./app.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { NZ_I18N, pt_BR } from "ng-zorro-antd/i18n";
import { registerLocaleData } from "@angular/common";
import pt from "@angular/common/locales/pt";
import { RouterModule } from "@angular/router";
import { AppRoutingModule } from "./app-routing.module";
import { CORE_API_URL } from "./core/core.constants";
import { environment } from "../environments/environment";
import { CoreModule } from "./core/core.module";
import { NzIconModule, NzMessageModule } from "ng-zorro-antd";

registerLocaleData(pt);

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    RouterModule,
    NzMessageModule,
    NzIconModule,
    ReactiveFormsModule,
    CoreModule.forRoot()
  ],
  providers: [
    { provide: NZ_I18N, useValue: pt_BR },
    { provide: CORE_API_URL, useValue: environment.coreApiUrl}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
