import {Inject, Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {CORE_API_URL} from "../core.constants";
import {Observable} from "rxjs";

@Injectable()
export class BaseUrlInterceptor implements HttpInterceptor {
  constructor(
    @Inject(CORE_API_URL) private coreUrl: string
  ) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let apiReq = request;
    let url = request.url;

    if (!url.startsWith("http")) {
      url = url.replace(/^\//, "");

      const pathArray = url.split("/");
      const module = pathArray.shift().toLowerCase();

      switch (module) {
        default:
          url = `${this.coreUrl}/api/${pathArray.join("/")}`;
      }

      apiReq = request.clone({url});
    }

    return next.handle(apiReq);
  }
}
