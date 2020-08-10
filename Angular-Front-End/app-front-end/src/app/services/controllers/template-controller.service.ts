import { Identifiable } from 'src/app/models/identifiable';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

// Shamelessly copied from Samuel Ferreira's Work. Nice Job Sam!
@Injectable({
    providedIn: "root"
})
export abstract class TemplateControllerService<T extends Identifiable> {

    private httpOptions = {
        headers: new HttpHeaders({
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        })
    };

    constructor(
        protected http: HttpClient
    ) {
    }

    protected abstract getApiUrlAll();
    protected abstract getApiUrlObject();

    getAll(): Observable<any> {
        return this.http.get(this.getApiUrlAll(), this.httpOptions).pipe(map((response)=> {
            return response;
        }));
    }

    getObject(id: string): Observable<any> {
        let url = `${this.getApiUrlObject()}/${id}`;
        return this.http.get(url, this.httpOptions).pipe(map((response)=> {
            return response;
        }));;
    }

    addObject(object: T): Observable<any> {
        return this.http.post(`${this.getApiUrlObject()}/create`, object, this.httpOptions).pipe(map((response)=> {
            return response;
        }));

        
    }

    deleteObject(object: T): Observable<any> {
        let url = `${this.getApiUrlObject()}/delete`;
        return this.http.post(url, object, this.httpOptions).pipe(map((response)=> {
            return response;
        }));;
    }
}