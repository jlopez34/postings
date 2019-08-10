import React, { Component } from "react";
import { Link, withRouter } from "react-router-dom";
import { Button, Container, Form, FormGroup, Input, Label } from "reactstrap";
import AppNavbar from "../AppNavbar";

class PostEdit extends Component{
    emptyItem={
        title:'',
        comments:'',
        likes:0,
        unlikes:0
    };

    constructor(props){
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount(){
        if(this.props.match.params.id !== 'new'){
            const post = await (await fetch(`/postings/${this.props.match.params.id}`)).json();
            this.setState({ item: post});
        }
    }

    handleChange(event){
        const target = event.target;
        const value = target.value;
        const title = target.name;
        let item = {...this.state.item};
        item[title] = value;
        console.log(item);
        this.setState({item});
    }

    async handleSubmit(event){
        event.preventDefault();
        const {item} = this.state;

        await fetch('/postings/', {
            method: (item.id) ? 'PUT':'POST',
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item)
        });
        this.props.history.push('/postings/');
    }

    render(){
        const {item} = this.state;
        const title =  <h2>{item.id? 'Edit Post':'Add Post'}</h2>

        return <div>
            <AppNavbar />
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="title">Title</Label>
                        <Input type="text" name="title" id="title" value={item.title || ''} onChange={this.handleChange} autoComplete="title" />
                    </FormGroup>
                    <FormGroup>
                        <Label for="comments">Comments</Label>
                        <Input type="textarea" name="comments" id="comments" value={item.comments || ''} onChange={this.handleChange} />
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>
                        <Button colar="secondary" tag={Link} to="/postings/">Cancel</Button> 
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(PostEdit);