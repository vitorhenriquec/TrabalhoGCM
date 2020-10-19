import {ModuleWithProviders, NgModule, Optional, SkipSelf} from "@angular/core";
import {NzMessageModule} from "ng-zorro-antd";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {NgxsModule} from "@ngxs/store";
import {NgxsLoggerPluginModule} from "@ngxs/logger-plugin";
import {BaseUrlInterceptor} from "./interceptors/base-url.interceptor";
import {ErrorInterceptor} from "./interceptors/error.interceptor";

@NgModule({
  declarations: [],
  imports: [
    HttpClientModule,
    NzMessageModule,
    NgxsModule.forRoot([], {developmentMode: !environment.production}),
    NgxsLoggerPluginModule.forRoot()
  ]
})
export class CoreModule {
  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error("CoreModule is already loaded. Import it in the AppModule only");
    }
  }

  static forRoot(): ModuleWithProviders {
    return {
      ngModule: CoreModule,
      providers: [
        {
          provide: HTTP_INTERCEPTORS,
          useClass: BaseUrlInterceptor,
          multi: true
        },
        {
          provide: HTTP_INTERCEPTORS,
          useClass: ErrorInterceptor,
          multi: true
        }
      ]
    };
  }
}
