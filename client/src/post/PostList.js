import React, { Component } from "react";
import { Button, Container, Table } from "reactstrap";
import AppNavbar from '../AppNavbar';
import { Link } from "react-router-dom";


class PostList extends Component {
    constructor(props) {
        super(props);
        this.state = { posts: [], isLoading: true };
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        this.setState({ isLoading: true });

        fetch('/postings/')
            .then(response => response.json())
            .then(data => this.setState({ posts: data, isLoading: false }));
    }

    async remove(id) {
        await fetch(`/postings/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedPost = [...this.state.posts].filter(post => post.id !== id);
            this.setState({ posts: updatedPost });
        });
    }

    async updateLike(post) {
        post.likes = post.likes + 1;

        await fetch('/postings/', {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(post)
        });
        this.props.history.push('/postings/');
    }




    render() {
        const { posts, isLoading } = this.state;

        if (isLoading) {
            return <p>Loading...</p>
        }

        const postList = posts.map(post => {
            const title = `${post.title || ''}`;
            const postID = `/postings/${post.id}`;
            return <div className="card gedf-card">
                <div class="card-header">
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="mr-2">
                                <img class="rounded-circle" width="45" src="https://picsum.photos/50/50" alt="" />
                            </div>
                            <div class="ml-2">
                                <div class="h5 m-0">@Segware</div>
                                <div class="h7 text-muted">{title}</div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="card-body">
                    <a class="card-link" href={postID}>
                        <h5 class="card-title">{title}</h5>
                    </a>

                    <p class="card-text">
                        {post.comments}
                    </p>
                </div>
                <div class="card-footer">
                    <Button size="sm" color="primary" onClick={() => this.updateLike(post)}>{post.likes} Likes</Button>
                    <div class="float-right">
                        <Button size="sm" color="secondary" onClick={() => this.remove(post.id)}>Delete</Button>
                    </div>
                </div>
            </div>
        });

        return (
            <div>
                <AppNavbar />
                <Container className="container-fluid gedf-wrapper">
                    <div class="row">
                        <div className="col-md-3 float-right">
                        </div>
 
                        <div className="col-md-7">
                            <Table className="mt-4">
                                <thead>
                                    <tr>
                                        <th width="50%">
                                            <div class="btn-group float-right">
                                                <Button color="success" class="glyphicon btn-glyphicon glyphicon-plus img-circle text-success" tag={Link} to="/postings/new">share</Button>
                                            </div>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {postList}
                                </tbody>
                            </Table>
                        </div>


                    </div>
                </Container>
            </div>
        );

    }

}

export default PostList;