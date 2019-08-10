import React, { Component } from 'react';
import './App.css';
import Home from "./Home";
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import PostList from './post/PostList';
import PostEdit from "./post/PostEdit";

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home} />
          <Route path='/postings/' exact={true} component={PostList} />
          <Route path='/postings/:id' component={PostEdit} />
        </Switch>
      </Router>
    ) 
  }
}

export default App;
