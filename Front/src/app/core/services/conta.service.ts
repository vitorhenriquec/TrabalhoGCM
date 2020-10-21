import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Conta } from "../models/conta.model";

@Injectable()
export class ContaService {
  private url = "/core/v1/conta";

  constructor(
    private http: HttpClient
  ) {}

  findAll(): Observable<Conta[]> {
    return this.http.get<Conta[]>(this.url);
  }

  findOne(id: number): Observable<Conta> {
    return this.http.get<Conta>(`${this.url}/${id}`);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

  save(conta: Conta): Observable<number> {
    return this.http.post<number>(this.url, conta);
  }

  transfer(transferencia): Observable<void> {
    return this.http.post<void>(`${this.url}/transferir`, transferencia);
  }
}
