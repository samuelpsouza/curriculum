import React, { Component } from 'react';
import { withStyles } from '@material-ui/core/styles';
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import './App.css';

import Major from './components/major/Major';
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
    const { classes } = this.props;
    return (
      <div className="App">
        <AppBar />

        <Fab color="primary" aria-label="Add" className={classes.fab}>
          <AddIcon />
        </Fab>

        {
          this.state.majors.map(major => {
            return (
              <Major major={major} />
            );
          })
        }
      </div>
    );
  }
}

export default withStyles(styles)(App);
