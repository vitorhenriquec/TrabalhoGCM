import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {NzMessageService} from "ng-zorro-antd";
import {catchError} from "rxjs/operators";

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(
    private message: NzMessageService
  ) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      catchError(err => {
        const error = err.error;

        if (error && error.hasOwnProperty("empty") && !error.empty) {
          if (error.errorPresent) {
            error.errorMessages.forEach(it => {
              this.message.error(it.message);
            });
          }

          if (error.warningPresent) {
            error.warningMessages.forEach(it => {
              this.message.warning(it.message);
            });
          }

          if (error.infoPresent) {
            error.infoMessages.forEach(it => {
              this.message.info(it.message);
            });
          }
        } else {
          this.message.error("Ocorreu um erro ao processar o comando. Tente novamente.");
        }

        return throwError(error);
      })
    );
  }

}
