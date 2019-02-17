import React, { Component } from 'react';
import './App.css';

import Major from './components/major/Major';
import AppBar from './components/app-bar/AppBar';

class App extends Component {
  state = {
    majors: []
  }

  componentWillMount(){
    fetch('http://localhost:8080/majors', {
      headers: {
        "Content-Type": "application/json"
      }
    }).then(response => {
      console.log(response)
    })
  }

  render() {
    return (
      <div className="App">
        <AppBar />

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

export default App;
