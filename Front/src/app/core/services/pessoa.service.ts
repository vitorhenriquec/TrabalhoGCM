import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Pessoa} from "../models/pessoa.model";

@Injectable()
export class PessoaService {
  private url = "/core/v1/pessoa";

  constructor(
    private http: HttpClient
  ) {}

  findAll(): Observable<Pessoa[]> {
    return this.http.get<Pessoa[]>(this.url);
  }

  findOne(id: number): Observable<Pessoa> {
    return this.http.get<Pessoa>(`${this.url}/${id}`);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

  save(pessoa: Pessoa): Observable<number> {
    return this.http.post<number>(this.url, pessoa);
  }
}
