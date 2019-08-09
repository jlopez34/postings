import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs/Observable";

@Injectable()
export class PostService {
  public API = '//localhost:8080';
  public POST_API = this.API + '/postings/';

  constructor(private http: HttpClient) { }

  getAll(): Observable<any>{
    return this.http.get(this.POST_API);
  }

  get(id: String){
    return this.http.get(this.POST_API + id);
  }


  save(post: any): Observable<any>{
    let result: Observable<Object>;
    if (post['id']) {
      post.comments = 'Testing';
      post.likes = 1;
      post.unlikes = 0;
      console.log(post);
      result = this.http.put(this.POST_API, post);
    } else {
      result = this.http.post(this.POST_API, post);
    }
    return result;
  }

  remove(id: string){
    return this.http.delete(this.POST_API+id);
  }

}
