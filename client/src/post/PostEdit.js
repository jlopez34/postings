import React, { Component } from "react";
import { Link, withRouter } from "react-router-dom";
import { Button, Container, Form, FormGroup, Input, Label } from "reactstrap";
import AppNavbar from "../AppNavbar";

class PostEdit extends Component {
    emptyItem = {
        title: '',
        comments: '',
        likes: 0,
        unlikes: 0
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const post = await (await fetch(`/postings/${this.props.match.params.id}`)).json();
            this.setState({ item: post });
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const title = target.name;
        let item = { ...this.state.item };
        item[title] = value;
        console.log(item);
        this.setState({ item });
    }

    async handleSubmit(event) {
        event.preventDefault();
        const { item } = this.state;

        await fetch('/postings/', {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item)
        });
        this.props.history.push('/postings/');
    }

    render() {
        const { item } = this.state;
        const title = <h2>{item.id ? 'Edit Post' : 'Add Post'}</h2>

        return <div>
            <AppNavbar />
            <Container>
                <div class="card gedf-card">
                    <div class="card-header">
                        <ul class="nav nav-tabs card-header-tabs" id="myTab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="posts-tab" data-toggle="tab" href="#posts" role="tab"
                                    aria-controls="posts" aria-selected="true">Make
                                    a publication</a>
                            </li>
                        </ul>
                    </div>
                    <div class="card-body">
                        <Form onSubmit={this.handleSubmit}>
                            <FormGroup class="form-group">
                                <Label class="sr-only" for="title">Title</Label>
                                <Input type="text" name="title" placeholder="What is the title?" id="title" value={item.title || ''} onChange={this.handleChange} autoComplete="title" />
                            </FormGroup>
                            <FormGroup class="form-group">
                                <Label class="sr-only" for="comments">Comments</Label>
                                <Input type="textarea" name="comments" placeholder="What are you thinking?" id="comments" value={item.comments || ''} onChange={this.handleChange} />
                            </FormGroup>
                            <FormGroup class="form-group">
                                <Button color="primary" type="submit">Save</Button><span> </span>
                                <Button colar="secondary" tag={Link} to="/postings/">Cancel</Button>
                            </FormGroup>
                        </Form>
                    </div>
                </div>
            </Container>
        </div>
    }
}

export default withRouter(PostEdit);