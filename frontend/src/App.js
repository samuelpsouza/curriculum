import React, { Component } from 'react';
import './App.css';

import Major from './components/major/Major';
import AppBar from './components/app-bar/AppBar';

class App extends Component {
  render() {
    return (
      <div className="App">
        <AppBar />

        <Major />
      </div>
    );
  }
}

export default App;
