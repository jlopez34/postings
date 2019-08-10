import React, { Component } from "react";
import { Button, ButtonGroup, Container, Table } from "reactstrap";
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

    render() {
        const { posts, isLoading } = this.state;

        if (isLoading) {
            return <p>Loading...</p>
        }

        const postList = posts.map(post => {
            const title = `${post.title || ''}`;
            return <tr key={post.id}>
                <td>
                    <Button size="sm" color="info" tag={Link} to={"/postings/" + post.id}>{post.likes}</Button><span>   </span>
                    <Button size="sm" color="danger" onClick={() => this.remove(post.id)}>{post.unlikes}</Button>
                </td>
                <td style={{ whiteSpace: 'nowrap' }}>{title}</td>
                <td>{post.comments}</td>
                <td><ButtonGroup>
                    <Button size="sm" color="primary" tag={Link} to={"/postings/" + post.id}>Edit</Button>
                    <Button size="sm" color="secondary" onClick={() => this.remove(post.id)}>Delete</Button>
                </ButtonGroup></td>
            </tr>
        });

        return (
            <div>
                <AppNavbar />
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/postings/new">Add Post</Button>
                    </div>
                    <h3>Posting List...</h3>
                    <Table className="mt-4">
                        <thead>
                            <tr>
                                <th width="2%"></th>
                                <th width="10%">Title</th>
                                <th width="20%">Comments</th>
                                <th width="5%">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {postList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );

    }

}

export default PostList;