import React, { Component } from 'react';
import AppRouter from './AppRouter';
import { withStyles } from '@material-ui/core/styles';
import './App.css';

import AppBar from './components/app-bar/AppBar';

const styles = theme => ({
  fab: {
    margin: theme.spacing.unit,
  },
  extendedIcon: {
    marginRight: theme.spacing.unit,
  },
});


class App extends Component {
  state = {
    majors: []
  }

  componentWillMount(){
    fetch('http://localhost:8080/majors', {
      headers: {
        "Content-Type": "application/json"
      }
    })
    .then(response => response.json())
    .then(response => {
      this.setState({...this.state, majors: response.data.content})
    })
  }

  render() {
    return (
      <div className="App">
        <AppBar />
        <AppRouter />
      </div>
    );
  }
}

export default withStyles(styles)(App);
