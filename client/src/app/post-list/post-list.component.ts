import { Component, OnInit } from '@angular/core';
import { PostService } from '../shared/post/post.service';
import { GiphyService } from "../shared/giphy/giphy.service";


@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {
  posts: Array<any>;

  constructor(private postService: PostService, private giphyService: GiphyService) { }

  ngOnInit() {
    this.postService.getAll().subscribe(data => {
      this.posts = data;
      for( const post of this.posts){
        this.giphyService.get(post.title).subscribe( url => post.giphyUrl = url);
      }
    });
  }

}
